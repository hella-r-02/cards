package main.service;

import java.util.List;

import main.entity.Category;

public interface CategoryService {
    List<Category> findAllCategories();
}
