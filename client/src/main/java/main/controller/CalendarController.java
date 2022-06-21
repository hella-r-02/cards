package main.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import main.dto.LevelDto;
import main.entity.Category;
import main.entity.Folder;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";

    @GetMapping()
    public String getLevels(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        LevelDto[] levels = restTemplate.exchange(domain + "level", HttpMethod.GET, entity, LevelDto[].class).getBody();
        for (LevelDto level : levels) {
            Folder folder = restTemplate.exchange(domain + "folder/level_id/" + level.getId(), HttpMethod.GET, entity, Folder.class).getBody();
            if (folder != null) {
                Category category = restTemplate.exchange(domain + "category/folder/" + folder.getId(), HttpMethod.GET, entity, Category.class).getBody();
                level.setCategory(category);
                level.setFolder(folder);
            }

        }
        model.addAttribute("levels", levels);
        return "calendar/calendar";
    }
}
