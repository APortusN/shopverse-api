package com.technova.shopverse;

import com.technova.shopverse.model.Category;
import com.technova.shopverse.model.Product;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopverseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopverseApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductRepository productRepository, CategoryRepository categoryRepository) {
		return args -> {

			//Product
			productRepository.save(new Product(null, "Laptop Lenovo", "Notebook 15 pulgadas", 850.0));
			productRepository.save(new Product(null, "Mouse Logitech", "Mouse inalámbrico", 25.5));
			productRepository.save(new Product(null, "Monitor Samsung", "Monitor 24 pulgadas", 199.99));

			//Category
			categoryRepository.save(new Category(null, "Tecnología", "Productos electrónicos y computación"));
			categoryRepository.save(new Category(null, "Hogar", "Artículos para el hogar y decoración"));
			categoryRepository.save(new Category(null, "Indumentaria", "Ropa y accesorios"));
		};
	}

}
