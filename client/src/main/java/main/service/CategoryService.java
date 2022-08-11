package main.service;

import main.entity.Category;

public interface CategoryService {
    Category getCategoryByFolderId(Long id);
}
