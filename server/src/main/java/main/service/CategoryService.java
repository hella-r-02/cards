package main.service;

import java.util.List;

import main.entity.Category;

public interface CategoryService {
    List<Category> findAllCategories();

    Category findByFolderId(Long id);

    void editCategory(Long id, String name);

    void deleteById(Long id);

    void addCategory(String name);
}
