package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.Price;
import com.web_app.cefion.repository.PriceRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/price")
public class PriceRestController {
    private final PriceRepository priceRepository;

    public PriceRestController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @GetMapping
    public ResponseEntity<?> getPrice() {
        List<Price> prices = priceRepository.findAll();
        Map<Object, Object> response = new HashMap<>();
        for (Price price: prices) {
            response.put(price.getCoin(), DTOController.price_to_DTO(price));
        }
        return ResponseEntity.ok(response);
    }
}

//    total_volume - объем торгов
//    market_cap - капитализация
//    market_cap_change_24h_in_currency - в валюте
//    market_cap_change_percentage_24h - в процентах
//
//    current_price /btc /usd - текущая цена
//    price_change_percentage_1h_in_currency - изменение за час
//    price_change_percentage_24h_in_currency - за день
//    price_change_percentage_7d_in_currency - 7д
//    price_change_percentage_30d_in_currency - 30д
