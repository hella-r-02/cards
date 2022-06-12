package main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    RestTemplate restTemplate;
    private String domain = "http://localhost:8080/";
}
