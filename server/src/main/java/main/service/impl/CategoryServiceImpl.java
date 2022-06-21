package main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Category;
import main.repository.CategoryRepository;
import main.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findByFolderId(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findByFolderId(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public void editCategory(Long id, String name) {
        categoryRepository.updateCategory(id, name);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void addCategory(String name) {
        categoryRepository.addCategory(name);
    }
}
