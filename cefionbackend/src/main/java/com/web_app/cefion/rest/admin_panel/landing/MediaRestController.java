package com.web_app.cefion.rest.admin_panel.landing;

import com.web_app.cefion.model.landing.Media;
import com.web_app.cefion.model.landing.Person;
import com.web_app.cefion.model.news.News;
import com.web_app.cefion.repository.MediaRepository;
import com.web_app.cefion.rest.DTO.DTOController;
import com.web_app.cefion.rest.DTO.MediaDTO;
import com.web_app.cefion.rest.DTO.ModelUpdate;
import com.web_app.cefion.rest.DTO.NewsDTO;
import com.web_app.cefion.rest.admin_panel.ANews;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/landing/media")
public class MediaRestController {

    private final String imgPath;
    private final MediaRepository mediaRepository;

    public MediaRestController(MediaRepository mediaRepository, @Value("${img.path.media}") String imgPath) {
        this.mediaRepository = mediaRepository;
        this.imgPath = imgPath;
    }

    @GetMapping
    public List<MediaDTO> getAll() {
        return mediaRepository.findAll().stream().map(DTOController::media_to_DTO).collect(Collectors.toList());
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<Resource> getImg(@PathVariable Integer id) {
        try {
            Media media = mediaRepository.findById(id).orElseThrow(() -> new NullPointerException("News doesn't exists"));
            File file = new File(imgPath + media.getImg_name());
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.ok().body(null);
        }
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String create(@RequestPart("file") MultipartFile file, @RequestPart("media") MediaDTO mediaDTO) {
        String imgName = ANews.saveFile(file, imgPath);
        if(imgName != null) {
            try {
                mediaRepository.save(DTOController.DTO_to_media(mediaDTO, imgName));
            } catch (Exception e) {
                return "Media save error.";
            }
            return "Success";
        } else {
            return "File save error.";
        }
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String update(@RequestPart("file") MultipartFile file, @RequestPart("media") MediaDTO mediaDTO) {
        String imgName = ANews.saveFile(file, imgPath);
        if (imgName != null) {
            return mediaRepository.findById(mediaDTO.getId())
                    .map(media -> {
                        String error = "";
                        try {
                            new File(imgPath + media.getImg_name()).delete();
                        } catch (Exception e) {
                            error = "Old file doesn't exist.";
                        }

                        try {
                            mediaRepository.save(ModelUpdate.update_media(media, mediaDTO, imgName));
                        } catch (Exception e) {
                            return "Media update error. " + error;
                        }
                        return "Media was updated. " + error;
                    })
                    .orElse("Media doesn't exist");
        } else {
            return "File save error.";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return mediaRepository.findById(id)
                .map(media -> {
                    String err = "Success";
                    try {
                        boolean st = new File(imgPath + media.getImg_name()).delete();
                        if(!st) {
                            err = "Old file doesn't exist.";
                        }
                    } catch (Exception e) {
                        err = "File error.";
                    }
                    mediaRepository.deleteById(id);
                    return err;
                }).orElse("News doesn't exist");
    }
}
