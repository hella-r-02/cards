package main.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Level;
import main.service.LevelService;

@RestController
@RequestMapping("/level")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Level>> getLevelsByFolderId(@PathVariable("id") Long id) {
        List<Level> levels = levelService.findLevelsByFolderId(id);
        levels.sort(Comparator.comparingLong(Level::getNum_of_level)
                .thenComparingLong(Level::getNum_of_level));
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping(value = "card/{id}")
    public ResponseEntity<Level> getLevelWithCardsThatReadyToRepeat(@PathVariable("id") Long id) {
        Level level = levelService.getLevelWithCardsThatReadyToRepeat(id);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @PostMapping(value = "date/{id}")
    public void updateDate(@PathVariable("id") Long id) {
        levelService.updateLevel(id);
    }

    @GetMapping()
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        levels.sort(Comparator.<Level>comparingLong(level1 -> level1.getNext_replay().getTime())
                .thenComparingLong(level2 -> level2.getNext_replay().getTime()));
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/levels")
    public ResponseEntity<List<Level>> getIsNotEmptyLevels() {
        List<Level> levels = levelService.getAllLevelsWithCards();
        levels.sort(Comparator.<Level>comparingLong(level1 -> level1.getNext_replay().getTime())
                .thenComparingLong(level2 -> level2.getNext_replay().getTime()));
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @PostMapping(value = "/add/folder/{id}")
    public void addLevelsByFolderId(@PathVariable("id") Long id, @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date,
                                    @RequestParam("currentNumOfLevels") int currentNumOfLevels) {
        levelService.addNewLevel(id, date, currentNumOfLevels);
    }

    @PostMapping(value = "find_level")
    public ResponseEntity<Level> findLevelByFolderIdAndNumOFLevel(@RequestParam("folderId") Long folderId,
                                                                  @RequestParam("numOfLevels") int numOfLevels) {
        Level level = levelService.findLevelByFolderIdAndNumOFLevel(folderId, numOfLevels);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @PostMapping(value = "delete/{id}")
    public ResponseEntity<Level> deleteById(@PathVariable Long id) {
        levelService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
