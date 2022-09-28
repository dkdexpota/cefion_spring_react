package com.web_app.cefion.repository;

import com.web_app.cefion.model.news.Price;
import com.web_app.cefion.rest.DTO.news.PriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    Price findByCoin(String coin);
}
