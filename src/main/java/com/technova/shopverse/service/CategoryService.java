package com.technova.shopverse.service;

import com.technova.shopverse.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    Optional<CategoryDTO> getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDto);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDto);
    CategoryDTO deleteCategory(Long id);
    CategoryDTO getCategoryDTOById(Long id);
}
