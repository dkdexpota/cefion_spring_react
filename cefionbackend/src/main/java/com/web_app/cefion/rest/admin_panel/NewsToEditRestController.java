package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.NewsToEdit;
import com.web_app.cefion.repository.NewsToEditRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news_edit")
@PreAuthorize("hasAuthority('copy_write:posts')")
public class NewsToEditRestController {
    private final NewsToEditRepository newsToEditRepository;

    @Autowired
    public NewsToEditRestController(NewsToEditRepository newsToEditRepository) {
        this.newsToEditRepository = newsToEditRepository;
    }

    @GetMapping
    public List<NewsDTO> getAll() {
        return newsToEditRepository.findAll().stream()
                .map(DTOController::newsToEdit_to_DTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NewsDTO getOne(@PathVariable Integer id) {
         return newsToEditRepository.findNewsToEditById(id).map(DTOController::newsToEdit_to_DTO)
                .orElseThrow(() -> new NullPointerException("News doesn't exists"));
    }

    @PostMapping
    public String create(@RequestBody NewsDTO newsDTO) {
        NewsToEdit newsToEdit = DTOController.DTO_to_newsToEdit(newsDTO);
        try {
            newsToEditRepository.save(newsToEdit);
        } catch (Exception e) {
            return "News save error.";
        }
        return "Success";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody NewsDTO newsDTO, @PathVariable Integer id) {
        return newsToEditRepository.findById(id)
                .map(newsToEdit -> {
                    try {
                        newsToEditRepository.save(ModelUpdate.update_newsToEdit(newsToEdit, newsDTO));
                    } catch (Exception e) {
                        return "News update error.";
                    }
                    return "News was updated";
                })
                .orElseGet(() -> {
                    NewsToEdit newsToEdit = DTOController.DTO_to_newsToEdit(newsDTO);
                    newsToEdit.setId(id);
                    try {
                    newsToEditRepository.save(newsToEdit);
                    } catch (Exception e) {
                        return "News create error.";
                    }
                    return "News was created";
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        newsToEditRepository.deleteById(id);
    }
}
