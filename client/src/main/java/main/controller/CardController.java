package main.controller;


import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import main.entity.Card;
import main.entity.Folder;
import main.utils.CardUtils;

@Controller
@RequestMapping("/card")
public class CardController {
    private final RestTemplate restTemplate;
    private final String domain = "http://localhost:8080/";
    private final String tempFolder = "/Users/alenaryzova/Documents/cards/cards/client/src/main/resources/temp/";

    public CardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCardsByLevelId(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Card[] listOfCards = restTemplate.exchange(domain + "card/" + id, HttpMethod.GET, entity, Card[].class).getBody();
        Folder folder = restTemplate.exchange(domain + "folder/level_id/" + id, HttpMethod.GET, entity, Folder.class).getBody();
        if (listOfCards != null && listOfCards.length != 0) {
            model.addAttribute("cards", listOfCards);
            model.addAttribute("folderId", folder.getId());
            model.addAttribute("levelId", id);
            return "card/cards";
        } else {
            return "redirect:/level/" + folder.getId();
        }
    }

    @PostMapping(value = "/success/{id}")
    @ResponseBody
    public ResponseEntity<Card> successCards(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "card/up/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/failure/{id}")
    @ResponseBody
    public ResponseEntity<Card> failureCards(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "card/down/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<Card> deleteById(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "card/delete/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add/{id}")
    public String addCard(@PathVariable("id") Long folderId,
                          @RequestParam("file_question") MultipartFile multipartFileQuestion,
                          @RequestParam("text_question") String textQuestion,
                          @RequestParam("file_answer") MultipartFile multipartFileAnswer,
                          @RequestParam("text_answer") String textAnswer) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("textQuestion", textQuestion);
        map.add("textAnswer", textAnswer);

        File fileQuestion = new File(tempFolder + multipartFileQuestion.getName() + ".png");
        multipartFileQuestion.transferTo(fileQuestion);
        FileSystemResource fileSystemResourceQuestion = new FileSystemResource(fileQuestion);
        map.add("byteQuestion", fileSystemResourceQuestion);

        File fileAnswer = new File(tempFolder + multipartFileAnswer.getName() + ".png");
        multipartFileAnswer.transferTo(fileAnswer);
        FileSystemResource fileSystemResourceAnswer = new FileSystemResource(fileAnswer);
        map.add("byteAnswer", fileSystemResourceAnswer);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(domain + "card/add/" + folderId, entity, String.class);
        fileAnswer.deleteOnExit();
        fileQuestion.deleteOnExit();
        return "redirect:/level/" + folderId;

    }

    @PostMapping(value = "/edit/{id}")
    public String editCard(@PathVariable("id") Long cardId,
                           @RequestParam("file_question") MultipartFile multipartFileQuestion,
                           @RequestParam("text_question") String textQuestion,
                           @RequestParam("file_answer") MultipartFile multipartFileAnswer,
                           @RequestParam("text_answer") String textAnswer) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Folder folder = restTemplate.exchange(domain + "folder/find/card/" + cardId, HttpMethod.GET, entity, Folder.class).getBody();

        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        FileSystemResource fileSystemResourceQuestion = CardUtils.createTempFileSystemResource(multipartFileQuestion);
        map.add("byteQuestion", fileSystemResourceQuestion);
        FileSystemResource fileSystemResourceAnswer = CardUtils.createTempFileSystemResource(multipartFileAnswer);
        map.add("byteAnswer", fileSystemResourceAnswer);
        map.add("textQuestion", textQuestion);
        map.add("textAnswer", textAnswer);

        HttpEntity<MultiValueMap<String, Object>> entityPost = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(domain + "card/edit/" + cardId, entityPost, String.class);
        return "redirect:/level/" + folder.getId();
    }
}
