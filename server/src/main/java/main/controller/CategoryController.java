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

import main.entity.Category;
import main.entity.Folder;
import main.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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

    @PostMapping("/edit/{id}")
    public ResponseEntity<Folder> editCategory(@PathVariable("id") Long id, @RequestParam("name") String name) {
        categoryService.editCategory(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Category> addCategory(@RequestParam("name") String name) {
        categoryService.addCategory(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
