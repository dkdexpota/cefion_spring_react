package com.web_app.cefion.repository;

import com.web_app.cefion.model.faq.Chapter;
import com.web_app.cefion.model.faq.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

}
