package com.web_app.cefion.rest.admin_panel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/landing")
public class LandingRestController {
    @GetMapping("/header")
    public String get_header() {return null;}
    @PutMapping("/header")
    public String update_header() {return null;}

    @GetMapping("/footer")
    public String get_footer() {return null;}
    @PutMapping("/footer")
    public String update_footer() {return null;}

    @GetMapping("/road_map")
    public String get_road_map() {return null;}
    @PutMapping("/road_map")
    public String update_road_map() {return null;}

    @GetMapping("/faqs")
    public String get_faqs() {return null;}
    @PutMapping("/faqs")
    public String update_faqs() {return null;}

    @GetMapping("/about")
    public String get_about() {return null;}
    @PutMapping("/about")
    public String update_about() {return null;}

    @GetMapping("/media")
    public String get_media() {return null;}
    @PutMapping("/media")
    public String update_media() {return null;}
}
