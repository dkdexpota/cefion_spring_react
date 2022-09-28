package com.web_app.cefion.rest.DTO.news;

import lombok.Data;

@Data
public class PriceDTO {
    double total_volume;
    double market_cap;
    double market_cap_change_24h_in_currency;
    double market_cap_change_percentage_24h;
    double current_price_btc;
    double current_price_usd;
    double price_change_percentage_1h_in_currency;
    double price_change_percentage_24h_in_currency;
    double price_change_percentage_7d_in_currency;
    double price_change_percentage_30d_in_currency;
}
