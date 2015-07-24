package com.exadel.training.repository.impl;

import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface UserFeedbackRepository extends JpaRepository <UserFeedback, Long> {
    List<UserFeedback> findFeedbackByUserOrderByDateAsc(User user);
}
