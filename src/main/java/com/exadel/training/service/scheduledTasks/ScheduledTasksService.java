package com.exadel.training.service.scheduledTasks;

import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.NotificationTrainingModel;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.notification.MessageFabric;
import com.exadel.training.notification.mail.WrapperNotificationMail;
import com.exadel.training.notification.sms.WrapperNotificationSMS;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.service.TrainingService;
import com.twilio.sdk.TwilioRestException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 20.07.2015.
 */

@Service
@EnableScheduling
public class ScheduledTasksService {

    @Autowired
    WrapperNotificationMail wrapperNotificationMail;

    @Autowired
    WrapperNotificationSMS wrapperNotificationSMS;

    @Autowired
    TrainingService trainingService;

    @Autowired
    TrainingRepository trainingRepository;

    public ScheduledTasksService() {
    }

    private int getHoursBeforeTraining(NotificationTrainingModel notificationTrainingModel) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date date = notificationTrainingModel.getDate();
        DateTime dateTime = new DateTime(DateTime.parse(sdf.format(date)));
        int hoursBeetween = Hours.hoursBetween(DateTime.now(), dateTime).getHours();
        return  hoursBeetween;
    }

    private boolean shouldBeCanceled(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) {
        if(listeners.size()/notificationTrainingModel.getCapacity() < 0.5) {
            return true;
        }
        return false;
    }

    private int emptyPlaces(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) {
        return notificationTrainingModel.getCapacity() - listeners.size();
    }

    private void cancelTraining(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) throws MessagingException, NoSuchFieldException {
        Training training = trainingService.getTrainingByName(notificationTrainingModel.getName());
        training.setState(StateTraining.parseToInt("Canceled"));
        trainingRepository.save(training);
        for (UserShort listener : listeners) {

            wrapperNotificationMail.send(listener.getEmail(), MessageFabric.getMessage(MessageFabric.messageType.Canceled,training.getName()));
        }
        UserShort traininer = notificationTrainingModel.getTrainer();
        wrapperNotificationMail.send(traininer.getEmail(), MessageFabric.getMessage(MessageFabric.messageType.Canceled,training.getName()));
    }

    private void notificateByEmail(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners, MessageFabric.messageType messageType) throws MessagingException {
        for (UserShort listener : listeners) {
            wrapperNotificationMail.send(listener.getEmail(), MessageFabric.getMessage(messageType, notificationTrainingModel.getName()));
        }
        UserShort traininer = notificationTrainingModel.getTrainer();
        wrapperNotificationMail.send(traininer.getEmail(), MessageFabric.getMessage(messageType, notificationTrainingModel.getName()));
    }

    private void notificateBySms(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners, MessageFabric.messageType messageType) throws TwilioRestException {
        for(UserShort listener: listeners) {
                wrapperNotificationSMS.send(listener.getNumberPhone(), MessageFabric.getMessage(messageType, notificationTrainingModel.getName()));
        }
        UserShort traininer = notificationTrainingModel.getTrainer();
            wrapperNotificationSMS.send(traininer.getNumberPhone(), MessageFabric.getMessage(messageType, notificationTrainingModel.getName()));
    }

    private void race(NotificationTrainingModel notificationTrainingModel) throws TwilioRestException {
        Training training = trainingService.getTrainingByName(notificationTrainingModel.getName());
        List<UserShort> spareListeners = UserShort.parseUserShortList(training.getSpareUsers());
        for(UserShort spareListener: spareListeners) {
                wrapperNotificationSMS.send(spareListener.getNumberPhone(), MessageFabric.getMessage(MessageFabric.messageType.Race,training.getName()));
        }
    }

    private void hasPased(NotificationTrainingModel notificationTrainingModel) throws NoSuchFieldException {
        Training training = trainingService.getTrainingByNameAndDate(notificationTrainingModel.getName(), notificationTrainingModel.getDate());
        int state = training.getState();
        if (getHoursBeforeTraining(notificationTrainingModel) < 0 &&  (state == StateTraining.parseToInt("InProcess") || state == StateTraining.parseToInt("Ahead"))) {
            training.setState(StateTraining.parseToInt("Finished"));
            trainingRepository.save(training);
            long trainingNumber = trainingService.getTrainingNumberByDate(notificationTrainingModel.getName(), notificationTrainingModel.getDate());
            long trainingsCount = trainingService.getTrainingsCount(notificationTrainingModel.getName());
            if ( trainingNumber == trainingsCount){
                Training parent = trainingService.getTrainingByName(notificationTrainingModel.getName());
                parent.setState(StateTraining.parseToInt("Finished"));
                trainingRepository.save(parent);
            }
        }
    }

    @Scheduled(fixedDelay = 3600000)
    public void doSomething() throws ParseException, NoSuchFieldException, MessagingException, TwilioRestException {
        List<Training> trainings = trainingService.getValidTrainingsExceptParent();
        if (!trainings.isEmpty()) {
            List<NotificationTrainingModel> notificationTrainingModels = NotificationTrainingModel.parseTrainingList(trainings);
            for (NotificationTrainingModel notificationTrainingModel : notificationTrainingModels) {
                int hoursBeetween = getHoursBeforeTraining(notificationTrainingModel);
                List<UserShort> listeners = notificationTrainingModel.getListeners();
                switch (hoursBeetween) {
                    case 24: {
                        if (shouldBeCanceled(notificationTrainingModel, listeners)) {
                            cancelTraining(notificationTrainingModel, listeners);
                        } else {
                            notificateByEmail(notificationTrainingModel, listeners, MessageFabric.messageType.OneDayLeft);
                        }
                        break;
                    }
                    case 3: {
                        if (emptyPlaces(notificationTrainingModel, listeners) > 0) {
                            race(notificationTrainingModel);
                        }
                        break;
                    }
                    case 1: {
                        notificateBySms(notificationTrainingModel, listeners, MessageFabric.messageType.OneHourLeft);
                        break;
                    }
                    default: {
                        hasPased(notificationTrainingModel);
                        break;
                    }
                }
            }
        }
    }
}