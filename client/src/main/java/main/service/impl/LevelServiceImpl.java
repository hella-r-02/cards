package main.service.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import main.config.MainConfig;
import main.dto.LevelDto;
import main.entity.Level;
import main.service.LevelService;

@Service
public class LevelServiceImpl implements LevelService {
    private final RestTemplate restTemplate;

    public LevelServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Level[] getLevelsByFolderId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "level/" + id, HttpMethod.GET, entity, Level[].class).getBody();
    }

    @Override
    public Level getLevelWithCardsThatReadyToRepeat(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "level/card/" + id, HttpMethod.GET, entity, Level.class).getBody();
    }

    @Override
    public void updateDate(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "level/date/" + id, entity, String.class);
    }

    @Override
    public LevelDto[] getIsNotEmptyLevels() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(MainConfig.DOMAIN + "level/levels", HttpMethod.GET, entity, LevelDto[].class).getBody();
    }
}
