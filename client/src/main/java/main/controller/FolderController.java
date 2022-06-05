package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping()
    public String getArticles(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Folder[] listOfFolders = restTemplate.exchange("http://localhost:8080/folder", HttpMethod.GET, entity, Folder[].class).getBody();
        model.addAttribute("folders", listOfFolders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "folder/folders.html";
    }
}
