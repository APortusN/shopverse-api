package com.technova.shopverse;

import com.technova.shopverse.model.Category;
import com.technova.shopverse.model.Product;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ShopverseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopverseApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductRepository productRepository, CategoryRepository categoryRepository) {
		return args -> {

			Category tecnologia = categoryRepository.save(new Category("Tecnologia", "Productos electronicos y computacion"));
			Category hogar = categoryRepository.save(new Category("Hogar", "Articulos para el hogar y decoracion"));
			Category indumentaria = categoryRepository.save(new Category("Indumentaria", "Ropa y accesorios"));

			// Tecnologia
			Product p1 = new Product("Laptop Lenovo", "Notebook 15 pulgadas", 850.0);
			p1.setCategory(tecnologia);

			Product p2 = new Product("Mouse Logitech", "Mouse inalambrico", 25.5);
			p2.setCategory(tecnologia);

			Product p3 = new Product("Monitor Samsung", "Monitor 24 pulgadas", 199.99);
			p3.setCategory(tecnologia);

			// Hogar
			Product p4 = new Product("Aspiradora Philips", "Aspiradora ciclonica sin bolsa", 150.0);
			p4.setCategory(hogar);

			Product p5 = new Product("Lampara de pie", "Lampara decorativa de salon", 89.99);
			p5.setCategory(hogar);

			// Indumentaria
			Product p6 = new Product("Zapatillas deportivas", "Zapatillas para running", 120.0);
			p6.setCategory(indumentaria);

			productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

		    //.save --> create y update
		};
	}

}
