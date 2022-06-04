package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.News;
import com.web_app.cefion.repository.NewsRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsRestController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public List<NewsDTO> getAll() {
        return (newsRepository.findAll().stream().map(DTOController::news_to_DTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public NewsDTO getOne(@PathVariable Integer id) {
        News news = newsRepository.findNewsById(id).orElseThrow(() ->
                new UsernameNotFoundException("News doesn't exists"));
        return DTOController.news_to_DTO(news);
    }


    @PreAuthorize("hasAuthority('edit:posts')")
    @PostMapping
    public String create(@RequestBody NewsDTO newsDTO) {
        News news = DTOController.DTO_to_news(newsDTO);
        try {
            newsRepository.save(news);
        } catch (Exception e) {
            return "News save error.";
        }
        return "Success";
    }

    @PreAuthorize("hasAuthority('edit:posts')")
    @PutMapping("/{id}")
    public String update(@RequestBody NewsDTO newsDTO, @PathVariable Integer id) {
        return newsRepository.findById(id)
                .map(news -> {
                    try {
                        newsRepository.save(ModelUpdate.update_news(news, newsDTO));
                    } catch (Exception e) {
                        return "News update error.";
                    }
                    return "News was updated";
                })
                .orElseGet(() -> {
                    News news = DTOController.DTO_to_news(newsDTO);
                    news.setId(id);
                    try {
                        newsRepository.save(news);
                    } catch (Exception e) {
                        return "News create error.";
                    }
                    return "News was created";
                });
    }

    @PreAuthorize("hasAuthority('edit:posts')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        newsRepository.deleteById(id);
    }
}
