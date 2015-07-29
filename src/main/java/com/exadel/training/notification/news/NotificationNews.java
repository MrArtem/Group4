package com.exadel.training.notification.news;

import com.exadel.training.model.News;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

/**
 * Created by HP on 28.07.2015.
 */
public interface NotificationNews {

    void sendNews(News news);
    void sendNews(String action, String with, String who);
    void sendNews(String action, User user, Training training);
}