package main.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import main.config.MainConfig;
import main.entity.Card;
import main.service.CardService;
import main.utils.CardUtils;

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

    @Override
    public Card[] getCardsByLevelIdAndDate(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(MainConfig.DOMAIN + "card/" + id, HttpMethod.GET, entity, Card[].class).getBody();
    }

    @Override
    public void upLevel(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "card/up/" + id, entity, String.class);
    }

    @Override
    public void downLevel(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "card/down/" + id, entity, String.class);
    }

    @Override
    public void deleteById(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(MainConfig.DOMAIN + "card/delete/" + id, entity, String.class);
    }

    @Override
    public void addCard(Long folderId, MultipartFile multipartFileQuestion, String textQuestion, MultipartFile multipartFileAnswer, String textAnswer) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("textQuestion", textQuestion);
            map.add("textAnswer", textAnswer);

            File fileQuestion = new File(MainConfig.TEMP_FOLDER + multipartFileQuestion.getName() + ".png");
            multipartFileQuestion.transferTo(fileQuestion);
            FileSystemResource fileSystemResourceQuestion = new FileSystemResource(fileQuestion);
            map.add("byteQuestion", fileSystemResourceQuestion);

            File fileAnswer = new File(MainConfig.TEMP_FOLDER + multipartFileAnswer.getName() + ".png");
            multipartFileAnswer.transferTo(fileAnswer);
            FileSystemResource fileSystemResourceAnswer = new FileSystemResource(fileAnswer);
            map.add("byteAnswer", fileSystemResourceAnswer);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
            restTemplate.postForEntity(MainConfig.DOMAIN + "card/add/" + folderId, entity, String.class);
            fileAnswer.deleteOnExit();
            fileQuestion.deleteOnExit();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void editCard(Long cardId, MultipartFile multipartFileQuestion, String textQuestion, MultipartFile multipartFileAnswer, String textAnswer) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            FileSystemResource fileSystemResourceQuestion = CardUtils.createTempFileSystemResource(multipartFileQuestion);
            map.add("byteQuestion", fileSystemResourceQuestion);
            FileSystemResource fileSystemResourceAnswer = CardUtils.createTempFileSystemResource(multipartFileAnswer);
            map.add("byteAnswer", fileSystemResourceAnswer);
            map.add("textQuestion", textQuestion);
            map.add("textAnswer", textAnswer);

            HttpEntity<MultiValueMap<String, Object>> entityPost = new HttpEntity<>(map, httpHeaders);
            restTemplate.postForEntity(MainConfig.DOMAIN + "card/edit/" + cardId, entityPost, String.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
