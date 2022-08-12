package main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.entity.Category;
import main.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping()
    public String getCategories(Model model) {
        Category[] listOfCategories = categoryService.getCategories();
        model.addAttribute("categories", listOfCategories);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);
        return "category/categories";
    }

    @PostMapping("edit/{id}")
    public String editCategory(@PathVariable("id") Long id, @RequestParam("name") String name) {
        categoryService.editCategory(id, name);
        return "redirect:/category";
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public String addCategory(@RequestParam("name") String name) {
        categoryService.addCategory(name);
        return "redirect:/category";
    }
}
