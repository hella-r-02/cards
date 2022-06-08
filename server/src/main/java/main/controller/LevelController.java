package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }
}
