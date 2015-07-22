package com.exadel.training.service;


import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.model.Training;

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
    List<Training> getAllTrainings();
    List<Training> getValidTrainingsByCategoryId(int id);
    List<Training> getValidTrainings();
    Training editTraining(Training training);
    Training approveTraining(String trainingName) throws NoSuchFieldException;
    List<Training> getTrainingsByNearestDate();
    Training deleteTrainingsByName(String trainingName);
    List<Training> searchTrainingsByName(String trainingName);
    List<Date> getDatesByTrainingName(String trainingName);
    List<Training> getTrainingForApprove();
    Training addSpareUser(String trainingName, String userName);
}
