package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.entity.Card;

@RestController()
@RequestMapping("/card_rest")
public class CardRestController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/success/{id}")
    public ResponseEntity<Card> successCards(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity("http://localhost:8080/card/up/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/failure/{id}")
    public ResponseEntity<Card> failureCards(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity("http://localhost:8080/card/down/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
