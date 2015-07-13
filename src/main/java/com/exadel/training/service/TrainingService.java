package com.exadel.training.service;


import com.exadel.training.model.Training;

import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
public interface TrainingService {
    Training addTraining(Training training);
    Training getTrainingByID(long id);
    Training getTrainingByName(String name);
    List<Training> getAllTrainings();
    List<Training> getAllTrainingsByCategoryName(String name);
    List<Training> getAllTrainingsByStateName(String name);
    Training editTraining(Training training);

}
