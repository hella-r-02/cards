package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Folder;
import main.service.FolderService;

@RestController
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    FolderService folderService;

    @GetMapping()
    public ResponseEntity<List<Folder>> getFolders() {
        List<Folder> list = folderService.findAllFolders();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
