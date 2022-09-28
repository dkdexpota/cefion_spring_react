package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.repository.NewsRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.news.NewsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news_draft")
@PreAuthorize("hasAuthority('copywriter')")
public class NewsToDraftRestController {
    private final NewsRepository newsRepository;
    private final String imgPath;
    public NewsToDraftRestController(NewsRepository newsRepository, @Value("${img.path.news}") String imgPath) {
        this.newsRepository = newsRepository;
        this.imgPath = imgPath;
    }

    @GetMapping("/{username}")
    public List<NewsDTO> getAll(@PathVariable String username) {
        return newsRepository.findAllByStatusAndAndAuthor(Status.DRAFT, username).stream()
                .map(DTOController::news_to_DTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NewsDTO getOneNews(@PathVariable Integer id) {
        return ANews.getOneNews(Status.DRAFT, id, newsRepository);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return ANews.delete(Status.DRAFT, id, imgPath, newsRepository);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<Resource> getImg(@PathVariable Integer id) {
        return ANews.getImg(Status.DRAFT, id, imgPath, newsRepository);
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        return ANews.update(Status.DRAFT, file, newsDTO, imgPath, newsRepository);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String create(@RequestPart("file") MultipartFile file, @RequestPart("news") NewsDTO newsDTO) {
        return ANews.create(Status.DRAFT, file, newsDTO, imgPath, newsRepository);
    }
}
