package main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Card;
import main.entity.Folder;
import main.entity.Level;
import main.service.CardService;
import main.service.LevelService;
import main.utils.CardUtils;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @Autowired
    LevelService levelService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Card>> getCardsByLevelIdAndDate(@PathVariable("id") Long id) {
        List<Card> cards = cardService.findBYLevelIDAndDate(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping(value = "folder/{id}")
    public ResponseEntity<List<Card>> getCardsByFolderId(@PathVariable("id") Long id) {
        List<Card> cards = cardService.findCardsByFolderId(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping(value = "up/{id}")
    public void upLevel(@PathVariable Long id) {
        Card card = cardService.findById(id);
        if (card != null) {
            Level level = card.getLevel();
            if (level != null) {
                Folder folder = level.getFolder();
                if (folder != null) {
                    if (folder.getNumOfLevels() >= level.getNum_of_level()) {
                        Level nextLevel = levelService.findNextLevel(folder.getId(), level.getNum_of_level());
                        if (nextLevel != null) {
                            Date newDate = CardUtils.getNextDate(card.getNext_replay(), nextLevel.getNext_replay(), nextLevel.getNum_of_level());
                            cardService.updateCardByDateAndLevel(card.getId(), nextLevel.getId(), newDate);
                        }
                    }
                }
            }
        }
    }

    @PostMapping(value = "down/{id}")
    public void downLevel(@PathVariable Long id) {
        Card card = cardService.findById(id);
        if (card != null) {
            Level level = card.getLevel();
            if (level != null) {
                if (level.getNum_of_level() != 1) {
                    Folder folder = level.getFolder();
                    if (folder != null) {
                        Level prevLevel = levelService.findPrevLevel(folder.getId(), level.getNum_of_level());
                        if (prevLevel != null) {
                            Date newDate = CardUtils.getNextDate(card.getNext_replay(), prevLevel.getNext_replay(), prevLevel.getNum_of_level());
                            cardService.updateCardByDateAndLevel(card.getId(), prevLevel.getId(), newDate);
                        }
                    }
                }
            }
        }
    }
}
