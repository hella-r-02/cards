package main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import main.entity.Card;
import main.entity.Folder;
import main.service.CardService;
import main.service.FolderService;

@Controller
@RequestMapping("/card")
public class CardController {
    private final FolderService folderService;
    private final CardService cardService;

    public CardController(FolderService folderService, CardService cardService) {
        this.folderService = folderService;
        this.cardService = cardService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCardsByLevelId(@PathVariable Long id, Model model) {
        Card[] listOfCards = cardService.getCardsByLevelIdAndDate(id);
        Folder folder = folderService.getFolderByLevelId(id);
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
        cardService.upLevel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/failure/{id}")
    @ResponseBody
    public ResponseEntity<Card> failureCards(@PathVariable Long id) {
        cardService.downLevel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<Card> deleteById(@PathVariable Long id) {
        cardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add/{id}")
    public String addCard(@PathVariable("id") Long folderId,
                          @RequestParam("file_question") MultipartFile multipartFileQuestion,
                          @RequestParam("text_question") String textQuestion,
                          @RequestParam("file_answer") MultipartFile multipartFileAnswer,
                          @RequestParam("text_answer") String textAnswer) {
        cardService.addCard(folderId, multipartFileQuestion, textQuestion, multipartFileAnswer, textAnswer);
        return "redirect:/level/" + folderId;

    }

    @PostMapping(value = "/edit/{id}")
    public String editCard(@PathVariable("id") Long cardId,
                           @RequestParam("file_question") MultipartFile multipartFileQuestion,
                           @RequestParam("text_question") String textQuestion,
                           @RequestParam("file_answer") MultipartFile multipartFileAnswer,
                           @RequestParam("text_answer") String textAnswer) {
        Folder folder = folderService.findByCardId(cardId);
        cardService.editCard(cardId, multipartFileQuestion, textQuestion, multipartFileAnswer, textAnswer);
        return "redirect:/level/" + folder.getId();
    }
}
