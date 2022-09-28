package com.web_app.cefion.controller;

import com.web_app.cefion.model.news.Price;
import com.web_app.cefion.repository.PriceRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceController {
    private final PriceRepository priceRepository;

    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void getPrice() throws IOException {
        int CONNECTION_TIMEOUT = 5 * 1000;
        List<String> coins = new ArrayList<>();
        coins.add("everscale");
        coins.add("velas");
        coins.add("ethereum");
        coins.add("bitcoin");
        for (String coin: coins) {
            Price price = priceRepository.findByCoin(coin);
            if (price == null) {
                price = new Price();
                price.setCoin(coin);
            }
            URL url = new URL("https://api.coingecko.com/api/v3/coins/" + coin + "?localization=false&tickers=false&community_data=false&developer_data=false");
            final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(CONNECTION_TIMEOUT);
            con.setReadTimeout(CONNECTION_TIMEOUT);
            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                final StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                Object obj = new JSONParser().parse(content.toString());
                JSONObject jo = (JSONObject) obj;
                jo = (JSONObject) jo.get("market_data");
                price.setTotal_volume(Double.parseDouble(((JSONObject) jo.get("total_volume")).get("usd").toString()));
                price.setMarket_cap(Double.parseDouble(((JSONObject) jo.get("market_cap")).get("usd").toString()));
                price.setMarket_cap_change_24h_in_currency(Double.parseDouble(((JSONObject) jo.get("market_cap_change_24h_in_currency")).get("usd").toString()));
                price.setMarket_cap_change_percentage_24h(Double.parseDouble(jo.get("market_cap_change_percentage_24h").toString()));
                price.setCurrent_price_btc(Double.parseDouble(((JSONObject) jo.get("current_price")).get("btc").toString()));
                price.setCurrent_price_usd(Double.parseDouble(((JSONObject) jo.get("current_price")).get("usd").toString()));
                price.setPrice_change_percentage_1h_in_currency(Double.parseDouble(((JSONObject) jo.get("price_change_percentage_1h_in_currency")).get("usd").toString()));
                price.setPrice_change_percentage_24h_in_currency(Double.parseDouble(((JSONObject) jo.get("price_change_percentage_24h_in_currency")).get("usd").toString()));
                price.setPrice_change_percentage_7d_in_currency(Double.parseDouble(((JSONObject) jo.get("price_change_percentage_7d_in_currency")).get("usd").toString()));
                price.setPrice_change_percentage_30d_in_currency(Double.parseDouble(((JSONObject) jo.get("price_change_percentage_30d_in_currency")).get("usd").toString()));
                priceRepository.save(price);
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

