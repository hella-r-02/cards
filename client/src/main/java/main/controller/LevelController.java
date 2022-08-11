package main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Card;
import main.entity.Category;
import main.entity.Folder;
import main.entity.Level;
import main.service.CardService;
import main.service.CategoryService;
import main.service.FolderService;
import main.service.LevelService;
import main.utils.LevelUtils;

@Controller
@RequestMapping("/level")
public class LevelController {
    private final LevelService levelService;
    private final CardService cardService;
    private final FolderService folderService;
    private final CategoryService categoryService;

    public LevelController(LevelService levelService,
                           CardService cardService,
                           FolderService folderService,
                           CategoryService categoryService) {
        this.levelService = levelService;
        this.cardService = cardService;
        this.folderService = folderService;
        this.categoryService = categoryService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getLevelsByFolderId(@PathVariable Long id, Model model) {
        Level[] listOfLevels = levelService.getLevelsByFolderId(id);
        Card[] listOfCards = cardService.getCardsByFolderId(id);
        Folder folder = folderService.getFolderById(id);
        Category category = categoryService.getCategoryByFolderId(id);
        Level[] listOfLevelsWithCards = new Level[listOfLevels.length];
        for (int i = 0; i < listOfLevelsWithCards.length; i++) {
            listOfLevelsWithCards[i] = levelService.getLevelWithCardsThatReadyToRepeat(listOfLevels[i].getId());
            if (listOfLevelsWithCards[i].getCards().size() == 0 && LevelUtils.isExpired(listOfLevels[i])) {
                levelService.updateDate(listOfLevels[i].getId());
            }
        }

        model.addAttribute("levels", listOfLevels);
        model.addAttribute("levelsWithCard", listOfLevelsWithCards);
        model.addAttribute("num_of_levels", listOfLevels.length);
        model.addAttribute("cards", listOfCards);
        model.addAttribute("title", category.getName() + '\\' + folder.getName());
        model.addAttribute("folder", folder);
        model.addAttribute("category", category);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "level/levels";
    }

    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity<Level> updateDate(@PathVariable("id") Long id) {
        levelService.updateDate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
