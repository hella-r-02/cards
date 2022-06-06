package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Card;
import main.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping(value = "/folder/{id}")
    public ResponseEntity<List<Card>> getCardsByFolderId(@PathVariable("id") Long id) {
        List<Card> cards = cardService.findByFolderId(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
