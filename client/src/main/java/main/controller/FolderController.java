package main.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import main.entity.Folder;

@Controller
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getFoldersByCategoryId(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Folder[] folderList = restTemplate.exchange(domain + "folder/" + id, HttpMethod.GET, entity, Folder[].class).getBody();
        model.addAttribute("folders", folderList);
        return "folder/folders";
    }
}
