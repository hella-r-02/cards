package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Category;
import main.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> list = categoryService.findAllCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<Category> findByFolderId(@PathVariable Long id) {
        Category category = categoryService.findByFolderId(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
