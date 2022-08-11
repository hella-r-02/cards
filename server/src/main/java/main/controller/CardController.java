package main.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import main.entity.Card;
import main.entity.Folder;
import main.entity.Level;
import main.service.CardService;
import main.service.LevelService;
import main.utils.CardUtils;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    private final LevelService levelService;

    public CardController(CardService cardService, LevelService levelService) {
        this.cardService = cardService;
        this.levelService = levelService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Card>> getCardsByLevelIdAndDate(@PathVariable("id") Long id) {
        List<Card> cards = cardService.findByLevelIdAndDate(id);
        System.out.println(cards);
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

    @PostMapping("/delete/{id}")
    public ResponseEntity<Card> deleteById(@PathVariable("id") Long id) {
        cardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/level")
    public ResponseEntity<Card> updateCardsBYLevel(@RequestParam("oldLevel") Long oldLevelId,
                                                   @RequestParam("newLevel") Long newLevelId) {
        cardService.updateCardsByLevel(oldLevelId, newLevelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    @ResponseBody
    public ResponseEntity<Card> addCard(@PathVariable("id") Long folderId,
                                        @RequestParam("textQuestion") String textQuestion,
                                        @RequestParam("byteQuestion") MultipartFile imgQuestion,
                                        @RequestParam("textAnswer") String textAnswer,
                                        @RequestParam("byteAnswer") MultipartFile imgAnswer) throws IOException {

        Level level = levelService.findLevelByFolderIdAndNumOFLevel(folderId, 1);
        if (!textQuestion.isEmpty()) {
            if (!textAnswer.isEmpty()) {
                cardService.addNewLevelTextQuestionTextAnswer(level.getId(), textQuestion, textAnswer, level.getNext_replay());
            } else {
                cardService.addNewLevelTextQuestionImgAnswer(level.getId(), textQuestion, imgAnswer.getBytes(), level.getNext_replay());
            }
        } else {
            if (!textAnswer.isEmpty()) {
                cardService.addNewLevelImgQuestionTextAnswer(level.getId(), imgQuestion.getBytes(), textAnswer, level.getNext_replay());
            } else {
                cardService.addNewLevelImgQuestionImgAnswer(level.getId(), imgQuestion.getBytes(), imgAnswer.getBytes(), level.getNext_replay());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<Card> editCard(@PathVariable("id") Long cardId,
                                         @RequestParam("textQuestion") String textQuestion,
                                         @RequestParam("byteQuestion") MultipartFile imgQuestion,
                                         @RequestParam("textAnswer") String textAnswer,
                                         @RequestParam("byteAnswer") MultipartFile imgAnswer) throws IOException {

        System.out.println("dkdldl");
        System.out.println(textQuestion + " " + imgQuestion.getBytes().length + " " + textAnswer + " " + imgAnswer.isEmpty());
        if (!textQuestion.isEmpty()) {
            if (!textAnswer.isEmpty()) {
                cardService.updateCardByQuestionAndAnswer(cardId, textQuestion, textAnswer);
            } else if (!imgAnswer.isEmpty()) {
                cardService.updateCardByQuestionAndAnswerImage(cardId, textQuestion, imgAnswer.getBytes());

            } else {
                cardService.updateCardByQuestion(cardId, textQuestion);
            }
        } else if (!textAnswer.isEmpty()) {
            if (!imgQuestion.isEmpty()) {
                cardService.updateCardByQuestionImageAndAnswer(cardId, imgQuestion.getBytes(), textAnswer);
            } else {
                cardService.updateCardByAnswer(cardId, textAnswer);
            }
        } else if (!imgQuestion.isEmpty()) {
            if (!imgAnswer.isEmpty()) {
                cardService.updateCardByQuestionImageAndAnswerImage(cardId, imgQuestion.getBytes(), imgAnswer.getBytes());
            } else {
                cardService.updateCardByQuestionImage(cardId, imgQuestion.getBytes());
            }
        } else {
            cardService.updateCardByAnswerImage(cardId, imgAnswer.getBytes());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


