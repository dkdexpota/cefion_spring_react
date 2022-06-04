package com.web_app.cefion.rest.admin_panel;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupsRestController {
    @PostMapping
    public String create() {
        return null;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return null;
    }
}
