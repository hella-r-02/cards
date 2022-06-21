package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Category;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";

    @RequestMapping()
    public String getCategories(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Category[] listOfCategories = restTemplate.exchange(domain + "category", HttpMethod.GET, entity, Category[].class).getBody();
        model.addAttribute("categories", listOfCategories);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "category/categories.html";
    }

    @PostMapping("edit/{id}")
    public String editCategory(@PathVariable("id") Long id, @RequestParam("name") String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(domain + "category/edit/" + id, entity, String.class);
        return "redirect:/category";
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.postForEntity(domain + "category/delete/" + id, entity, String.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public String addCategory(@RequestParam("name") String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
        restTemplate.postForEntity(domain + "category/add", entity, String.class);
        return "redirect:/category";
    }
}
