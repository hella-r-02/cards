package main.service.impl;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @Override
    public void addLevelsByFolderId(Long id, String date, int currentNumOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("date", date);
        map.add("currentNumOfLevels", currentNumOfLevels);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "level/add/folder/" + id, entity, String.class);
    }

    @Override
    public Level findLevelByFolderIdAndNumOFLevel(Long folderId, int numOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("folderId", folderId);
        map.add("numOfLevels", numOfLevels);
        HttpEntity<MultiValueMap<String, Object>> entityLevelFind = new HttpEntity<>(map, httpHeaders);
        return restTemplate.postForEntity(MainConfig.DOMAIN + "level/find_level", entityLevelFind, Level.class).getBody();
    }

    @Override
    public void deleteLevel(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entityLevel = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "level/delete/" + id, entityLevel, String.class);
    }
}
