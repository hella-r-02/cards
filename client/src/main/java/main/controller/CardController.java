package main.controller;

import java.util.Collections;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import main.entity.Card;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getArticleById(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Card[] cardList = restTemplate.exchange("http://localhost:8080/card/folder/" + id, HttpMethod.GET, entity, Card[].class).getBody();
        model.addAttribute("cards", cardList);
        return "card/cards";

    }
}
