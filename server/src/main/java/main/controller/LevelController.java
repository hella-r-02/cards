package main.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Level;
import main.service.LevelService;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    LevelService levelService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Level>> getLevelsByFolderId(@PathVariable("id") Long id) {
        List<Level> levels = levelService.findLevelsByFolderId(id);
        Collections.sort(levels, Comparator.<Level> comparingLong(level1->level1.getNum_of_level())
        .thenComparingLong(level2->level2.getNum_of_level()));
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
}
