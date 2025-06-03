package com.technova.shopverse.service.impl;

import com.technova.shopverse.dto.ProductDTO;
import com.technova.shopverse.model.Category;
import com.technova.shopverse.model.Product;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.repository.ProductRepository;
import com.technova.shopverse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .toList();
    }
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::new);
    }

    public ProductDTO createProduct(ProductDTO productDto) {
        Category category = categoryRepository.findByName(productDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada: " + productDto.getCategoryName()));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return new ProductDTO(saved);
    }

    public ProductDTO updateProduct(Long id, ProductDTO updatedDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + id + " no existe."));

        Category category = categoryRepository.findByName(updatedDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + updatedDto.getCategoryName()));

        product.setName(updatedDto.getName());
        product.setDescription(updatedDto.getDescription());
        product.setPrice(updatedDto.getPrice());
        product.setCategory(category);

        Product updated = productRepository.save(product);
        return new ProductDTO(updated);
    }

    public ProductDTO deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        ProductDTO productDto = new ProductDTO(product);
        productRepository.deleteById(id);

        return productDto;
    }

    public List<ProductDTO> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(ProductDTO::new)
                .toList();
    }

}
