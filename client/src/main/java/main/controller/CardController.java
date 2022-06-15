package main.controller;


import java.util.Base64;
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

import main.entity.Card;
import main.entity.Folder;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";
    private String typeOfImg = "data:image/png;base64,";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCardsByLevelId(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
            Card[] listOfCards = restTemplate.exchange(domain + "card/" + id, HttpMethod.GET, entity, Card[].class).getBody();
            if (listOfCards!=null && listOfCards.length != 0) {
                String[] listOfQuestion = new String[listOfCards.length];
                String[] listOfAnswer = new String[listOfCards.length];
                String[] imgQuestion = new String[listOfCards.length];
                String[] imgAnswer = new String[listOfCards.length];
                Base64.Encoder encoder = Base64.getEncoder();
                for (int i = 0; i < listOfCards.length; i++) {
                    if (listOfCards[i].getQuestion_image() != null) {
                        imgQuestion[i] = typeOfImg + encoder.encodeToString(listOfCards[i].getQuestion_image());
                    } else {
                    }
                    if (listOfCards[i].getAnswer_image() != null) {
                        imgAnswer[i] = typeOfImg + encoder.encodeToString(listOfCards[i].getAnswer_image());
                    } else {
                        imgAnswer[i] = null;
                    }
                }
                for (int i = 0; i < listOfQuestion.length; i++) {
                    listOfQuestion[i] = listOfCards[i].getQuestion();
                    listOfAnswer[i] = listOfCards[i].getAnswer();
                }
                model.addAttribute("cards", listOfCards);
                model.addAttribute("questions", listOfQuestion);
                model.addAttribute("answers", listOfAnswer);
                model.addAttribute("imgQuestion", imgQuestion);
                model.addAttribute("imgAnswer", imgAnswer);
                return "card/cards";
            } else {
                Folder folder = restTemplate.exchange(domain + "folder/level_id/" + id, HttpMethod.GET, entity, Folder.class).getBody();
                return "redirect:/level/" + folder.getId();
            }
        } catch (HttpClientErrorException exception) {
            return "error";
        }
    }
}
