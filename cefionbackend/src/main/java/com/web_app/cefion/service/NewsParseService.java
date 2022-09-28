package com.web_app.cefion.service;

import com.web_app.cefion.repository.NewsRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class NewsParseService {
    private final NewsRepository newsRepository;

    public NewsParseService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void getHackernoon() throws IOException {
        int CONNECTION_TIMEOUT = 5 * 1000;
        URL url = new URL("https://mo7dwh9y8c-dsn.algolia.net/1/indexes/*/queries?x-algolia-agent=Algolia%20for%20JavaScript%20(4.12.2)%3B%20Browser%20(lite)%3B%20JS%20Helper%20(3.7.0)%3B%20react%20(17.0.2)%3B%20react-instantsearch%20(6.22.0)&x-algolia-api-key=e0088941fa8f9754226b97fa87a7c340&x-algolia-application-id=MO7DWH9Y8C");
        final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        osw.write("{\"requests\":[{\"indexName\":\"stories\",\"params\":\"highlightPreTag=%3Cais-highlight-0000000000%3E&highlightPostTag=%3C%2Fais-highlight-0000000000%3E&query=&maxValuesPerFacet=10&hitsPerPage=11&clickAnalytics=true&page=0&facets=%5B%22tags%22%5D&tagFilters=&facetFilters=%5B%22tags%3Ablockchain%22%5D\"}]}");
        osw.flush();
        osw.close();
        os.close();
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
            jo = (JSONObject) ((JSONObject) jo.get("results")).get("hits");
            for (int i = 0; i < 11; i++) {
                if(newsRepository.findNewsByTitleEU(((JSONObject) jo.get(i)).get("title").toString()).isEmpty()) {
                    url = new URL("https://hackernoon.com/" + ((JSONObject) jo.get(i)).get("slug").toString());
                }
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

}
