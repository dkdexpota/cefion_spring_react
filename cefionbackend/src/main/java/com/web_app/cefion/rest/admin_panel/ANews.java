package com.web_app.cefion.rest.admin_panel;

import com.web_app.cefion.model.news.News;
import com.web_app.cefion.model.news.Status;
import com.web_app.cefion.model.news.Type;
import com.web_app.cefion.repository.NewsRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.NewsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

abstract public class ANews {

    public static String saveFile(MultipartFile file, String imgPath) {

        if (!file.isEmpty()) {
            try {
                File uploadDir = new File(imgPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String imgName = UUID.randomUUID() + "." + file.getOriginalFilename();
                file.transferTo(new File(imgPath + imgName));
                return imgName;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static List<NewsDTO> getAll(Status status, NewsRepository newsRepository) {
        return newsRepository.findAllByStatus(status).stream()
                .map(DTOController::news_to_DTO).collect(Collectors.toList());
    }

    public static List<NewsDTO> getPage(Status status, String type, Integer id, NewsRepository newsRepository) {
        type = type.toUpperCase();
        id++;
        return switch (type) {
            case "ALL" ->
                    newsRepository.findAllByStatus(PageRequest.of(id, 8), status)
                            .stream().map(DTOController::news_to_DTO).collect(Collectors.toList());
            case "BLOCKCHAIN", "NFT", "DEFI", "BUSINESS", "GAMEFI" ->
                    newsRepository.findAllByTypeAndStatus(PageRequest.of(id, 8), Type.valueOf(type), status)
                            .stream().map(DTOController::news_to_DTO).collect(Collectors.toList());
            default -> null;
        };
    }

    public static NewsDTO getOneNews(Status status, Integer id, NewsRepository newsRepository) {
        return newsRepository.findByIdAndStatus(id, status)
                .map(DTOController::news_to_DTO)
                .orElseThrow(() -> new NullPointerException("News doesn't exists"));
    }

    public static String delete(Status status, Integer id, String imgPath, NewsRepository newsRepository) {
        return newsRepository.findByIdAndStatus(id, status)
                .map(news -> {
                    String err = "Success";
                    try {
                        boolean st = new File(imgPath + news.getImg_name()).delete();
                        if(!st) {
                            err = "Old file doesn't exist.";
                        }
                    } catch (Exception e) {
                        err = "File error.";
                    }
                    newsRepository.deleteById(id);
                    return err;
                }).orElse("News doesn't exist");
    }

    public static ResponseEntity<Resource> getImg(Status status, Integer id, String imgPath, NewsRepository newsRepository) {
        try {
            News news = newsRepository.findByIdAndStatus(id, status).orElseThrow(() -> new NullPointerException("News doesn't exists"));
            File file = new File(imgPath + news.getImg_name());
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.ok().body(null);
        }
    }

    public static String update(Status status, MultipartFile file, NewsDTO newsDTO, String imgPath, NewsRepository newsRepository) {
        String imgName = ANews.saveFile(file, imgPath);
        if (imgName != null) {
            return newsRepository.findByIdAndStatus(newsDTO.getId(), status)
                    .map(news -> {
                        String error = "";
                        try {
                            new File(imgPath + news.getImg_name()).delete();
                        } catch (Exception e) {
                            error = "Old file doesn't exist.";
                        }

                        try {
                            newsRepository.save(ModelUpdate.update_news(news, newsDTO, imgName));
                        } catch (Exception e) {
                            return "News update error. " + error;
                        }
                        return "News was updated. " + error;
                    })
                    .orElse("News doesn't exist");
        } else {
            return "File save error.";
        }
    }

    public static void changeStatus(Status oldStatus, Status newStatus, Integer id, NewsRepository newsRepository) {
        newsRepository.findByIdAndStatus(id, oldStatus).map(news -> {
            news.setStatus(newStatus);
            newsRepository.save(news);
            return null;
        });
    }

    public static String create(Status status, MultipartFile file, NewsDTO newsDTO, String imgPath, NewsRepository newsRepository) {
        String imgName = saveFile(file, imgPath);
        if(imgName != null) {
            News news = DTOController.DTO_to_news(newsDTO, imgName);
            news.setStatus(status);
            try {
                newsRepository.save(news);
            } catch (Exception e) {
                return "News save error.";
            }
            return "Success";
        } else {
            return "File save error.";
        }
    }
}
