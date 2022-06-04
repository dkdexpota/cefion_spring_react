package com.web_app.cefion.repository;

import com.web_app.cefion.model.news.NewsToEdit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsToEditRepository extends JpaRepository<NewsToEdit, Integer> {
    Optional<List<NewsToEdit>> findNewsToEditsByAuthor(String author);
    Optional<NewsToEdit> findNewsToEditById(int id);
}
