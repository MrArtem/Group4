package com.exadel.training.service;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Role;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.model.LoginName;

import java.util.Date;
import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserService {

    Boolean checkUserByLogin(String login);
    Boolean checkUserByEmail(String email);
    Boolean checkSubscribeToTraining(Long trainingID, Long userID);
    Boolean isCoach(String login, String coachName);
    Boolean checkSubscribeToTraining(String trainingName, String login);
    Boolean whoIsUser(String login, long roleId);
    Boolean isMyTraining(String login, String trainingName);

    User getUserByID(long id);
    User findUserByLoginAndPassword(String name, long password);
    User findUserByLogin(String Login);
    User findUserByEmail(String email);
    Training findMyTraining(String login, String trainingName);

    List<Role> findRolesOfUser(String login);

    List<Training> selectAllTraining(String login);
    List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachTrue(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachFalse(String login, List<Integer> state);
    List<Training> selectAllTrainingBetweenDatesAndSortedByName(String login, Date from, Date to);
    List<Training> selectAllTrainingAndSortedByName(String login);

    List<Date> selectAllDateOfTrainingsBetweenDates(String login, Date from, Date to);
    List<Date> selectAllDateOfTrainings(String login);

    List<LoginName> selectAllLoginOfUsers();
    List<User> findUsersByRole(RoleType type) throws NoSuchFieldException;
    List<User> findUsersByRole(Long type);
    List<User> findAllCoachOfUser(String login);

    void saveUser(User user);
    void deleteUserTrainingRelationShip(String login, String trainingName);
    void insertUserTrainingRelationShip(String login, String trainingName);
    void insertNumberOfTelephone(String login, String number);
    void insertUser(User user);
}
