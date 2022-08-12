package main.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.entity.Category;
import main.entity.Folder;
import main.entity.Level;
import main.service.CardService;
import main.service.CategoryService;
import main.service.FolderService;
import main.service.LevelService;

@Controller
@RequestMapping("/folder")
public class FolderController {
    private final LevelService levelService;
    private final CardService cardService;
    private final FolderService folderService;
    private final CategoryService categoryService;

    public FolderController(LevelService levelService,
                            CardService cardService,
                            FolderService folderService,
                            CategoryService categoryService) {
        this.levelService = levelService;
        this.cardService = cardService;
        this.folderService = folderService;
        this.categoryService = categoryService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getFoldersByCategoryId(@PathVariable Long id, Model model) {
        Folder[] folderList = folderService.getFoldersByCategoryId(id);
        model.addAttribute("folders", folderList);
        return "folder/folders";
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<Folder> deleteById(@PathVariable Long id) {
        folderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public String getAllFolders(Model model) {
        Folder[] folderList = folderService.getAllFolders();
        if (folderList != null) {
            for (Folder folder : folderList) {
                Category category = categoryService.getCategoryByFolderId(folder.getId());
                folder.setCategory(category);
            }
            Arrays.sort(folderList, Comparator.<Folder>comparingLong(folder1 -> folder1.getCategory().getId())
                    .thenComparingLong(folder2 -> folder2.getCategory().getId()));
            model.addAttribute("folders", folderList);
        }
        return "library/folders";
    }

    @PostMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        if (name != null && numOfLevels > 0) {
            Folder folder = folderService.getFolderById(id);
            if (folder != null) {
                int currentNumOfLevels = folder.getNumOfLevels();
                if (currentNumOfLevels < numOfLevels) {
                    while (currentNumOfLevels < numOfLevels) {
                        currentNumOfLevels++;
                        Date date = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        levelService.addLevelsByFolderId(id, dateFormat.format(date), currentNumOfLevels);
                    }
                } else if (currentNumOfLevels > numOfLevels) {
                    Level lastLevel = levelService.findLevelByFolderIdAndNumOFLevel(folder.getId(), numOfLevels);
                    for (int i = currentNumOfLevels; i > numOfLevels; i--) {
                        Level tempLevel = levelService.findLevelByFolderIdAndNumOFLevel(folder.getId(), i);
                        Long tempLevelId = tempLevel.getId();
                        if (tempLevel.getCards() != null) {
                            for (int j = 0; j < tempLevel.getCards().size(); j++) {
                                cardService.updateLevel(tempLevel.getId(), lastLevel.getId());
                            }
                        }
                        levelService.deleteLevel(tempLevelId);
                    }
                }
                folderService.editFolder(id, name, numOfLevels);
            }
        }
        return "redirect:/level/" + id;
    }

    @PostMapping(value = "/add/{id}")
    public String addFolder(@PathVariable("id") Long categoryId, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        folderService.addFolder(categoryId, name, numOfLevels);
        Folder[] folderList = folderService.findFoldersByName(name);
        if (folderList != null && folderList.length != 0) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < numOfLevels; i++) {
                levelService.addLevelsByFolderId(folderList[folderList.length - 1].getId(),  dateFormat.format(calendar.getTime()), i + 1);
            }
            return "redirect:/level/" + folderList[folderList.length - 1].getId();
        }
        return "redirect:/category";
    }
}
