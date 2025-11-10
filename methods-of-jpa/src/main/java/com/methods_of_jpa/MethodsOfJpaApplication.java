package com.methods_of_jpa;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.methods_of_jpa.model.Product;
import com.methods_of_jpa.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class MethodsOfJpaApplication {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MethodsOfJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return (args) -> {
			var product = Product.builder()
					.productName("Iphone 17 pro max")
					.productBrand("Apple")
					.productPrice(150000.99)
					.build();

			// save
			// var savedProduct = productRepository.save(product);
			// System.out.println("saved product is " + savedProduct);

			// save all
			// var products = getProducts();
			// productRepository.saveAll(products);

			// count
			var numbersOfAvailableProducts = productRepository.count();
			System.out.println("/////////////////////" + numbersOfAvailableProducts);
			// Exists
			System.out.println("/////////////////////" + productRepository.exists(Example.of(product)));
			System.out.println(
					"/////////////////////" + productRepository.existsById("s1f19ae5e-33c0-48db-86d1-6c0954450414"));
			// delete
			productRepository.deleteById("s1f19ae5e-33c0-48db-86d1-6c0954450414");

			// FinD Allvar

			var existingProducts = productRepository.findAll();
			existingProducts.forEach(System.out::println);
			// existingProducts.forEach(p->system.out.println(p))
			// for(var p : existingProducts){
			// System.out.println(p);
			// }
			var sortedProductByName = productRepository.findAll(Sort.by(Direction.DESC, "productBrand"));
			sortedProductByName.forEach(System.out::println);

		};
	}

	private List<Product> getProducts() {
		return IntStream.range(0, 10)
				.mapToObj(i -> {
					return Product.builder()
							.productName("product - " + i)
							.productBrand("brand - " + i)
							.productPrice(1000.50 * i)
							.build();
				})
				.toList();

	}

}