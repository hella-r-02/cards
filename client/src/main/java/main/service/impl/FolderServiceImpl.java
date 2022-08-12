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

    @Override
    public Folder getFolderByLevelId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "folder/level_id/" + id, HttpMethod.GET, entity, Folder.class).getBody();
    }

    @Override
    public Folder findByCardId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "folder/find/card/" + id, HttpMethod.GET, entity, Folder.class).getBody();
    }

    @Override
    public Folder[] findFoldersByName(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "folder/find/name/" + name, HttpMethod.GET, entity, Folder[].class).getBody();
    }

    @Override
    public Folder[] getFoldersByCategoryId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(MainConfig.DOMAIN + "folder/" + id, HttpMethod.GET, entity, Folder[].class).getBody();
    }

    @Override
    public Folder[] getAllFolders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return restTemplate.exchange(MainConfig.DOMAIN + "folder", HttpMethod.GET, entity, Folder[].class).getBody();
    }

    @Override
    public void deleteById(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "folder/delete/" + id, entity, String.class);
    }

    @Override
    public void editFolder(Long id, String name, int numOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("numOfLevels", numOfLevels);
        map.add("name", name);
        HttpEntity<MultiValueMap<String, Object>> entityFolder = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "folder/edit/" + id, entityFolder, String.class);
    }

    @Override
    public void addFolder(Long categoryId, String name, int numOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("numOfLevels", numOfLevels);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "folder/add/" + categoryId, entity, String.class);
    }
}
