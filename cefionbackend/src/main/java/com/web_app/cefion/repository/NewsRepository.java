package com.web_app.cefion.repository;

import com.web_app.cefion.model.news.News;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.model.news.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findNewsById(int id);
    Optional<News> findByIdAndStatus(int id, Status status);

    List<News> findAllByStatus(Pageable pageable, Status status);
    List<News> findAllByTypeAndStatus(Pageable pageable, Type type, Status status);
    List<News> findAllByStatus(Status status);

    List<News> findAllByStatusAndAndAuthor(Status status, String author);

    Optional<News> findNewsByTitleEU(String title);
}
