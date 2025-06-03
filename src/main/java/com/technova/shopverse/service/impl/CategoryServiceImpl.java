package com.technova.shopverse.service.impl;

import com.technova.shopverse.dto.CategoryDTO;
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

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::new)
                .toList();
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryDTO::new);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO updatedDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La Categoria con ID " + id + " no existe."));

        category.setName(updatedDto.getName());
        category.setDescription(updatedDto.getDescription());

        Category updated = categoryRepository.save(category);
        return new CategoryDTO(updated);
    }

    public CategoryDTO deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        if (!category.getProducts().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar una categoría con productos asociados.");
        }

        CategoryDTO categoryDto = new CategoryDTO(category);

        categoryRepository.deleteById(id);

        return categoryDto;
    }

    public CategoryDTO getCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
        return new CategoryDTO(category);
    }

}
