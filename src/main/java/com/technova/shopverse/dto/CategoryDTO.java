package com.technova.shopverse.dto;

import com.technova.shopverse.model.Category;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> productNames;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.productNames = category.getProducts().stream().map(product -> product.getName()).toList();
    }

    public CategoryDTO(Long id) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

}
