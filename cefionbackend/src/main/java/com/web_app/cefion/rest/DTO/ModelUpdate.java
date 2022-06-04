package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.news.News;
import com.web_app.cefion.model.news.NewsToEdit;
import com.web_app.cefion.model.user.User;

import java.util.Date;

public abstract class ModelUpdate {
    public static NewsToEdit update_newsToEdit(NewsToEdit newsToEdit, NewsDTO newsDTO) {
        newsToEdit.setDate(new Date());
        newsToEdit.setEu_body(newsDTO.getEu_body());
        newsToEdit.setRu_body(newsDTO.getRu_body());
        newsToEdit.setRu_title(newsDTO.getRu_title());
        newsToEdit.setEu_title(newsDTO.getEu_title());
        return newsToEdit;
    }

    public static News update_news(News news, NewsDTO newsDTO) {
        news.setDate(new Date());
        news.setEu_body(newsDTO.getEu_body());
        news.setRu_body(newsDTO.getRu_body());
        news.setRu_title(newsDTO.getRu_title());
        news.setEu_title(newsDTO.getEu_title());
        return news;
    }

    public static User update_user(User user, UserDTO userDTO) {
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        return user;
    }
}
