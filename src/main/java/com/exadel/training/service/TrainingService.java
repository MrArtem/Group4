package com.exadel.training.service;


import com.exadel.training.controller.model.Training.LessonData;
import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
public interface TrainingService {

    Training addTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException, ParseException;
    Training getTrainingByID(long id);
    Training getTrainingByName(String name);
    Training getTrainingByNameAndUserLogin(String trainingName, String userLogin);
    Training getTrainingByNameAndDate(String trainingName, Date trainingDate);
    Training editTraining(TrainingForCreation trainingForCreation) throws ParseException, NoSuchFieldException;
    Training approveEditTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException;
    Training approveCreateTraining(TrainingForCreation trainingForCreation) throws ParseException, NoSuchFieldException;
    Training deleteTrainingsByName(String trainingName);
    Training addSpareUser(String trainingName, String userLogin);
    Training changeLessonDate(LessonData lessonData) throws ParseException;
    Training getEditedTrainingByName(String trainingName);

    List<Training> getAllTrainings();
    List<Training> getTrainingsByName(String trainingName);
    List<Training> getValidTrainingsByCategoryId(int id);
    List<Training> getValidTrainings();
    List<Training> getTrainingsByNearestDate();
    List<Training> getTrainingForApprove();
    List<Training> getTrainingsByCoach(String coachLogin);
    List<Training> getTrainingsByHighestRating();

    List<User> getUsersByTrainingName(String trainingName);
    List<User> getSpareUsersByTrainingName(String trainingName);
    List<User> getListenersByTrainingNameSortByName(String trainingName);

    List<Date> getDatesByTrainingName(String trainingName);
    List<Date> getDatesByTrainingNameBetweenDates(String trainingName, Date firstDate, Date secondDate);

    List<String> getPlacesByTrainingName(String trainingName);

    Integer getTrainingNumber(String trainingName, Date date);
    Integer getValidTrainingsNumberByCategory(Category category);
    Long getParentTrainingId(String trainingName);
    Boolean isSubscriber(String trainingName, String userLogin);


}
