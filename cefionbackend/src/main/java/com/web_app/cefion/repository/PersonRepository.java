package com.web_app.cefion.repository;

import com.web_app.cefion.model.landing.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person, Integer> {
}
