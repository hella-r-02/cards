package main.service;

import main.entity.Category;

public interface CategoryService {
    Category getCategoryByFolderId(Long id);

    Category[] getCategories();

    void editCategory(Long id, String name);

    void deleteById(Long id);

    void addCategory(String name);
}
