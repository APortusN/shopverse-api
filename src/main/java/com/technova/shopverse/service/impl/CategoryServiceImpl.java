package com.technova.shopverse.service.impl;

import com.technova.shopverse.model.Category;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public Optional<Category> getCategoryById(Long id) { return categoryRepository.findById(id); }

    public Category createCategory(Category category) {
        validateCategory(category);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updated) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("La Categoria con ID " + id + " no existe.");
        }

        validateCategory(updated);

        Category category = optionalCategory.get();
        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteById(id);
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio.");
        }
        if (category.getDescription() == null || category.getDescription().length() < 10) {
            throw new IllegalArgumentException("La descripcion debe tener al menos 10 caracteres.");
        }
    }

}
