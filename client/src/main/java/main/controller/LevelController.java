package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Level;

@Controller
@RequestMapping("/level")
public class LevelController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getLevelsByFolderId(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Level[] listOfLevels = restTemplate.exchange(domain + "level/" + id, HttpMethod.GET, entity, Level[].class).getBody();
        System.out.println(listOfLevels[0].getCards());
        model.addAttribute("levels",listOfLevels);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "level/levels.html";
    }
}
