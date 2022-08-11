package main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Folder;
import main.service.FolderService;

@RestController
@RequestMapping("/folder")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping()
    public ResponseEntity<List<Folder>> getFolders() {
        List<Folder> list = folderService.findAllFolders();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Folder>> getFoldersByCategoryId(@PathVariable("id") Long id) {
        List<Folder> folders = folderService.findFoldersByCategoryId(id);
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @GetMapping(value = "level/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable("id") Long id) {
        Folder folder = folderService.findById(id);
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    @GetMapping(value = "/level_id/{id}")
    public ResponseEntity<Folder> getFolderByLevelId(@PathVariable("id") Long id) {
        Folder folder = folderService.findByLevelId(id);
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Folder> deleteById(@PathVariable("id") Long id) {
        folderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Folder> editFolder(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        folderService.updateFolder(id, name, numOfLevels);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Folder> addFolder(@PathVariable("id") Long categoryId, @RequestParam("name") String name, @RequestParam("numOfLevels") int numOfLevels) {
        folderService.addFolder(categoryId, name, numOfLevels);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<Folder>> findByName(@PathVariable("name") String name) {
        List<Folder> folders = folderService.findByName(name);
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @GetMapping("/find/card/{id}")
    public ResponseEntity<Folder> findByCardId(@PathVariable("id") Long id) {
        Folder folder = folderService.FindByCardId(id);
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }
}
