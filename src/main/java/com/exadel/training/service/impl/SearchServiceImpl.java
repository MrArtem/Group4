package com.exadel.training.service.impl;

import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 29.07.2015.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public List<User> searchUsers(String name) {
        return userRepository.searchUsers("'" + name + "*'");
    }

    @Override
    public List<Training> searchTrainings(String searchWord) {
        return trainingRepository.searchTrainingByName("'" + searchWord + "*'");
    }
}
