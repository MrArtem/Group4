package com.exadel.training.service.impl;

import com.exadel.training.common.FeedbackType;
import com.exadel.training.controller.model.Feedback.CoachFeedbackADDModel;
import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.CoachFeedbackRepository;
import com.exadel.training.service.CoachFeedbackService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
@Service
public class CoachFeedbackServiceImpl implements CoachFeedbackService {
    @Autowired
    CoachFeedbackRepository coachFeedbackRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void addCoachFeedback(CoachFeedbackADDModel coachFeedbackModel, Date date) {
        String coachLogin = coachFeedbackModel.getCoachLogin();
        User coach = userService.findUserByLogin(coachLogin);
        String feedbackerLogin = coachFeedbackModel.getFeedbackerLogin();
        User feedbacker = userService.findUserByLogin(feedbackerLogin);
        CoachFeedback cfeedback = new CoachFeedback(coachFeedbackModel.isHowEnounceMaterial(), coachFeedbackModel.isExplainHardness(), coachFeedbackModel.isHighlightMain(), coachFeedbackModel.isInteresting(), coachFeedbackModel.isAskingQuestions(),
                coachFeedbackModel.isExplainHowToUseNew(), coachFeedbackModel.isCreativity(), coachFeedbackModel.isKindness(), coachFeedbackModel.isPatience(), coachFeedbackModel.isErudition(), coachFeedbackModel.isStyleOfTeaching(), coachFeedbackModel.getOther(),
                feedbacker, coach, date);
        cfeedback.setType(FeedbackType.getFeedbackType(cfeedback));
        coachFeedbackRepository.save(cfeedback);
    }

    @Override
    public List<CoachFeedback> getCoachFeedbacksOrderByDate(User coach) {
        return coachFeedbackRepository.findFeedbackByCoachOrderByDateAsc(coach);
    }

    @Override
    public CoachFeedback getCoachFeedbackByLoginsAndDate(String coachLogin, String feedbackerLogin, Date date) {
        return coachFeedbackRepository.findFeedbackByCoachAndDateAndFeedbacker(coachLogin, feedbackerLogin, date);
    }

    @Override
    public void deleteFeedback(String coachLogin, String feedbackerLogin, Date date) {
        CoachFeedback coachFeedback = coachFeedbackRepository.findFeedbackByCoachAndDateAndFeedbacker(coachLogin, feedbackerLogin, date);
        coachFeedbackRepository.delete(coachFeedback);
    }


}
