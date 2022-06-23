package main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Card;
import main.entity.Category;
import main.entity.Folder;
import main.entity.Level;
import main.utils.LevelUtils;

@Controller
@RequestMapping("/level")
public class LevelController {

    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";
    private String typeOfImg = "data:image/png;base64,";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getLevelsByFolderId(@PathVariable Long id, Model model) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Level[] listOfLevels = restTemplate.exchange(domain + "level/" + id, HttpMethod.GET, entity, Level[].class).getBody();
        Card[] listOfCards = restTemplate.exchange(domain + "card/folder/" + id, HttpMethod.GET, entity, Card[].class).getBody();
        Folder folder = restTemplate.exchange(domain + "folder/level/" + id, HttpMethod.GET, entity, Folder.class).getBody();
        Category category = restTemplate.exchange(domain + "category/folder/" + id, HttpMethod.GET, entity, Category.class).getBody();
        Level[] listOfLevelsWithCards = new Level[listOfLevels.length];
        for (int i = 0; i < listOfLevelsWithCards.length; i++) {
            if (listOfLevels[i].getCards().size() == 0 && LevelUtils.isExpired(listOfLevels[i])) {
                restTemplate.postForEntity(domain + "level/date/" + listOfLevels[i].getId(), entity, String.class);
            }
            listOfLevelsWithCards[i] = restTemplate.exchange(domain + "level/card/" + listOfLevels[i].getId(), HttpMethod.GET, entity, Level.class).getBody();
        }

        model.addAttribute("levels", listOfLevels);
        model.addAttribute("levelsWithCard", listOfLevelsWithCards);
        model.addAttribute("num_of_levels", listOfLevels.length);
        model.addAttribute("cards", listOfCards);
        model.addAttribute("title", category.getName() + '\\' + folder.getName());
        model.addAttribute("folder", folder);
        model.addAttribute("category", category);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "level/levels.html";
    }

    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<Level> updateDate(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "level/date/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
