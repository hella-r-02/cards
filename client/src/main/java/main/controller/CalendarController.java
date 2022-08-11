package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dto.LevelDto;
import main.entity.Category;
import main.entity.Folder;
import main.service.CategoryService;
import main.service.FolderService;
import main.service.LevelService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    private final LevelService levelService;
    private final FolderService folderService;
    private final CategoryService categoryService;

    public CalendarController(LevelService levelService, FolderService folderService, CategoryService categoryService) {
        this.levelService = levelService;
        this.folderService = folderService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getLevels(Model model) {
        LevelDto[] levels = levelService.getIsNotEmptyLevels();
        for (LevelDto level : levels) {
            Folder folder = folderService.getFolderByLevelId(level.getId());
            if (folder != null) {
                Category category = categoryService.getCategoryByFolderId(folder.getId());
                level.setCategory(category);
                level.setFolder(folder);
            }

        }
        model.addAttribute("levels", levels);
        return "calendar/calendar";
    }
}
