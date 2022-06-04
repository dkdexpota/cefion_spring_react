package com.web_app.cefion.rest.DTO;

import com.web_app.cefion.model.news.News;
import com.web_app.cefion.model.news.NewsToEdit;
import com.web_app.cefion.model.user.User;
import com.web_app.cefion.model.user.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public abstract class DTOController {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static NewsToEdit DTO_to_newsToEdit(NewsDTO newsDTO) {
        NewsToEdit newsToEdit = new NewsToEdit();
        newsToEdit.setDate(new Date());
        newsToEdit.setAuthor(newsDTO.getAuthor());
        newsToEdit.setEu_title(newsDTO.getEu_title());
        newsToEdit.setRu_title(newsDTO.getRu_title());
        newsToEdit.setEu_body(newsDTO.getEu_body());
        newsToEdit.setRu_body(newsDTO.getRu_body());
        return newsToEdit;
    }

    public static News DTO_to_news(NewsDTO newsDTO) {
        News news = new News();
        news.setDate(new Date());
        news.setAuthor(newsDTO.getAuthor());
        news.setEu_title(newsDTO.getEu_title());
        news.setRu_title(newsDTO.getRu_title());
        news.setEu_body(newsDTO.getEu_body());
        news.setRu_body(newsDTO.getRu_body());
        return news;
    }

    public static NewsDTO newsToEdit_to_DTO(NewsToEdit newsToEdit) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setDate(new Date());
        newsDTO.setAuthor(newsToEdit.getAuthor());
        newsDTO.setEu_title(newsToEdit.getEu_title());
        newsDTO.setRu_title(newsToEdit.getRu_title());
        newsDTO.setEu_body(newsToEdit.getEu_body());
        newsDTO.setRu_body(newsToEdit.getRu_body());
        return newsDTO;
    }

    public static NewsDTO news_to_DTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setDate(new Date());
        newsDTO.setAuthor(news.getAuthor());
        newsDTO.setEu_title(news.getEu_title());
        newsDTO.setRu_title(news.getRu_title());
        newsDTO.setEu_body(news.getEu_body());
        newsDTO.setRu_body(news.getRu_body());
        return newsDTO;
    }

    public static User DTO_to_user(RegistrationRequestDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(encoder.encode(requestDTO.getPassword()));
        user.setRole(requestDTO.getRole());
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public static UserDTO user_to_DTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }


}
