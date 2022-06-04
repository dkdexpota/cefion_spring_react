package com.web_app.cefion.repository;

import com.web_app.cefion.model.faq.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
