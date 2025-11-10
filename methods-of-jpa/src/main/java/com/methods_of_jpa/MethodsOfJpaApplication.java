package com.methods_of_jpa;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
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

			// SAVE
			// var savedProduct = productRepository.save(product);
			// System.out.println("saved product is " + savedProduct);

			// SAVE ALL
			// var products = getProducts();
			// productRepository.saveAll(products);

			// COUNT
			// var numberOfAvailableProducts = productRepository.count();
			// System.out.println("//////////////////////////////////" +
			// numberOfAvailableProducts);

			// Exists
			// System.out.println("////////////////////// " +
			// productRepository.exists(Example.of(product)));
			// System.out.println(".................... " +
			// productRepository.existsById("f3c93d7d-ed9a-42c1-a541-1460726c89fd"));

			// Delete
			// productRepository.deleteById("f3c93d7d-ed9a-42c1-a541-1460726c89fd");

			// FIND ALL (SELECT)
			// var existingProducts = productRepository.findAll();
			// existingProducts.forEach(System.out::println);
			// existingProducts.forEach(p -> System.out.println(p));
			// for (var p : existingProducts) {
			// System.out.println(p);
			// }

			// var sortedProductsByProductName =
			// productRepository.findAll(Sort.by(Direction.DESC, "productName"));
			// sortedProductsByProductName.forEach(System.out::println);
			// var productByPage = productRepository.findAll(PageRequest.of(1, 6,
			// Sort.by(Direction.ASC, "productName")));
			// productByPage.forEach(System.out::println);

			// Find By id -used for extracting single record
			// var existingProduct =
			// productRepository.findById("6a24b8cf-68bb-487e-864e-3a95fc1307ce").orElseThrow();
			// System.out.println("/////////////" + existingProduct);
			// Update
			var optProduct = productRepository.findById("6a24b8cf-68bb-487e-864e-3a95fc1307ce");
			if (optProduct.isPresent()) {
				var existingProduct = optProduct.get();
				existingProduct.setProductPrice(5555.55);
				productRepository.save(existingProduct);
			} else {
				System.out.println("Product not found");
			}
			/*
			 * Above methods are already given by jpa repository, but we want to execute
			 * some custom SQl query
			 * -JPQL (Java Persistence Query Language)
			 * -Navite Query or plain Sql
			 * - <Return Type> <keyword><property><Condition>(parameters)
			 */

		};
	}

	private List<Product> getProducts() {
		return IntStream.range(1, 10)
				.mapToObj(i -> {
					return Product.builder()
							.productName("product- " + i)
							.productBrand("brand- " + i)
							.productPrice(1000.50 * i)
							.build();
				})
				.toList();

		// var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		// return numbers.stream()
		// .map(number -> {
		// return Product.builder()
		// .productName("product- " + number)
		// .productBrand("brand- " + number)
		// .productPrice(1000.50 * number)
		// .build();
		// })
		// .toList();

	}
}