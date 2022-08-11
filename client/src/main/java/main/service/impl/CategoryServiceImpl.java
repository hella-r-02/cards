package main.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
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
}
