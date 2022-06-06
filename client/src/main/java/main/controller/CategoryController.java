package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Category;
import main.entity.Folder;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping()
    public String getCategories(Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        Category[] listOfCategories = restTemplate.exchange("http://localhost:8080/category", HttpMethod.GET, entity, Category[].class).getBody();
        model.addAttribute("categories", listOfCategories);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "category/categories.html";
    }
}
