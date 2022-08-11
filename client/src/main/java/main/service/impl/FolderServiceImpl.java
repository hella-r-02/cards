package main.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import main.config.MainConfig;
import main.entity.Folder;
import main.service.FolderService;

@Service
public class FolderServiceImpl implements FolderService {
    private final RestTemplate restTemplate;

    public FolderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Folder getFolderById(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "folder/level/" + id, HttpMethod.GET, entity, Folder.class).getBody();
    }
}
