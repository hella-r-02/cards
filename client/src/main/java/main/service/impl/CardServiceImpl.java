package main.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import main.config.MainConfig;
import main.entity.Card;
import main.service.CardService;

@Service
public class CardServiceImpl implements CardService {
    private final RestTemplate restTemplate;

    public CardServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Card[] getCardsByFolderId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "card/folder/" + id, HttpMethod.GET, entity, Card[].class).getBody();
    }
}
