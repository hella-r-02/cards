package main.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import main.config.MainConfig;
import main.entity.Category;
import main.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final RestTemplate restTemplate;

    public CategoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Category getCategoryByFolderId(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "category/folder/" + id, HttpMethod.GET, entity, Category.class).getBody();
    }

    @Override
    public Category[] getCategories() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "category", HttpMethod.GET, entity, Category[].class).getBody();
    }

    @Override
    public void editCategory(Long id, String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "category/edit/" + id, entity, String.class);
    }

    @Override
    public void deleteById(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "category/delete/" + id, entity, String.class);
    }

    @Override
    public void addCategory(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "category/add", entity, String.class);
    }
}
