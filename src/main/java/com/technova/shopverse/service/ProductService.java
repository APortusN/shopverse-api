package com.technova.shopverse.service;

import com.technova.shopverse.dto.ProductDTO;

import java.util.List;
import java.util.Optional;
public interface ProductService {

    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long id, ProductDTO updatedDto);
    ProductDTO deleteProduct(Long id);
    List<ProductDTO> getByCategoryId(Long categoryId);


}

