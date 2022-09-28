package com.web_app.cefion.model.news;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PRICE")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String coin;

    private double total_volume;
    private double market_cap;
    private double market_cap_change_24h_in_currency;
    private double market_cap_change_percentage_24h;
    private double current_price_btc;
    private double current_price_usd;
    private double price_change_percentage_1h_in_currency;
    private double price_change_percentage_24h_in_currency;
    private double price_change_percentage_7d_in_currency;
    private double price_change_percentage_30d_in_currency;
}
