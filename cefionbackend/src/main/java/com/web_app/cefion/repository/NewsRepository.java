package com.web_app.cefion.repository;

import com.web_app.cefion.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findNewsById(int id);
}
