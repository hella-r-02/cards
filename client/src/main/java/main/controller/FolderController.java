package main.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import main.entity.Card;
import main.entity.Category;
import main.entity.Folder;
import main.entity.Level;

@Controller
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getFoldersByCategoryId(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Folder[] folderList = restTemplate.exchange(domain + "folder/" + id, HttpMethod.GET, entity, Folder[].class).getBody();
        model.addAttribute("folders", folderList);
        return "folder/folders";
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "folder/delete/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public String getAllFolders(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Folder[] folderList = restTemplate.exchange(domain + "folder", HttpMethod.GET, entity, Folder[].class).getBody();
        if (folderList != null) {
            for (int i = 0; i < folderList.length; i++) {
                Category category = restTemplate.exchange(domain + "category/folder/" + folderList[i].getId(), HttpMethod.GET, entity, Category.class).getBody();
                folderList[i].setCategory(category);
            }
            Arrays.sort(folderList, Comparator.<Folder>comparingLong(folder1 -> folder1.getCategory().getId())
                    .thenComparingLong(folder2 -> folder2.getCategory().getId()));
            model.addAttribute("folders", folderList);
        }
        return "library/folders";
    }

    @PostMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        if (name != null && numOfLevels > 0) {
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            Folder folder = restTemplate.exchange(domain + "folder/level/" + id, HttpMethod.GET, entity, Folder.class).getBody();
            if (folder != null) {
                int currentNumOfLevels = folder.getNumOfLevels();
                if (currentNumOfLevels < numOfLevels) {
                    while (currentNumOfLevels < numOfLevels) {
                        currentNumOfLevels++;
                        Date date = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        map.add("date", dateFormat.format(date));
                        map.add("currentNumOfLevels", currentNumOfLevels);
                        HttpEntity<MultiValueMap<String, Object>> entityAddLevel = new HttpEntity<>(map, httpHeaders);
                        restTemplate.postForEntity(domain + "level/add/folder/" + id, entityAddLevel, String.class);
                    }
                } else if (currentNumOfLevels > numOfLevels) {
                    map.add("folderId", folder.getId());
                    map.add("numOfLevels", numOfLevels);
                    HttpEntity<MultiValueMap<String, Object>> entityLevelFind = new HttpEntity<>(map, httpHeaders);
                    Level lastLevel = restTemplate.postForEntity(domain + "level/find_level", entityLevelFind, Level.class).getBody();
                    for (int i = currentNumOfLevels; i > numOfLevels; i--) {
                        map.remove("numOfLevels");
                        map.add("numOfLevels", i);
                        HttpEntity<MultiValueMap<String, Object>> entityLevelFindEachLevel = new HttpEntity<>(map, httpHeaders);
                        Level tempLevel = restTemplate.postForEntity(domain + "level/find_level", entityLevelFindEachLevel, Level.class).getBody();
                        Long tempLevelId = tempLevel.getId();
                        if (tempLevel.getCards() != null) {
                            for (int j = 0; j < tempLevel.getCards().size(); j++) {
                                Card tempCard = tempLevel.getCards().get(j);
                                map.add("oldLevel", tempLevel.getId());
                                map.add("newLevel", lastLevel.getId());
                                HttpEntity<MultiValueMap<String, Object>> entityCard = new HttpEntity<>(map, httpHeaders);
                                restTemplate.postForEntity(domain + "card/update/level", entityLevelFindEachLevel, String.class);
                            }
                        }
                        HttpEntity<String> entityLevel = new HttpEntity<>(httpHeaders);
                        restTemplate.postForEntity(domain + "level/delete/" + tempLevelId, entityLevel, String.class);
                    }
                }
                map.remove("numOfLevels");
                map.add("numOfLevels", numOfLevels);
                map.add("name", name);
                HttpEntity<MultiValueMap<String, Object>> entityFolder = new HttpEntity<>(map, httpHeaders);
                restTemplate.postForEntity(domain + "folder/edit/" + id, entityFolder, String.class);

            }
        }
        return "redirect:/level/" + id;
    }

    @PostMapping(value = "/add/{id}")
    public String addFolder(@PathVariable("id") Long categoryId, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("numOfLevels", numOfLevels);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(domain + "folder/add/" + categoryId, entity, String.class);
        Folder[] folderList = restTemplate.exchange(domain + "folder/find/name/" + name, HttpMethod.GET, entity, Folder[].class).getBody();
        if (folderList != null && folderList.length != 0) {
            return "redirect:/level/" + folderList[folderList.length - 1].getId();
        }
        return "redirect:/category";
    }
}
