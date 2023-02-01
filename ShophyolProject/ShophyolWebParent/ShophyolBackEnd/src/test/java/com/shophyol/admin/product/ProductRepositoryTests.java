package com.shophyol.admin.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shophyol.common.entity.Brand;
import com.shophyol.common.entity.Category;
import com.shophyol.common.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateProduct() {

		Brand brand = entityManager.find(Brand.class, 3);
		Category category = entityManager.find(Category.class, 22);

		Product product = new Product();
		product.setName("Monster Abra A5 V13.3");
		product.setAlias("monster_abra_a5_v13.3");
		product.setShortDescription("Short description for Monster Abra A5 V13.3");
		product.setFullDescription("Full description for Monster Abra A5 V13.3");

		product.setBrand(brand);
		product.setCategory(category);

		product.setPrice(22000);
		product.setCost(20000);
		product.setEnabled(true);
		product.setInStock(true);

		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());

		Product savedProduct = repo.save(product);

		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateProduct1() {

		Brand brand = entityManager.find(Brand.class, 6);
		Category category = entityManager.find(Category.class, 5);

		Product product = new Product();
		product.setName("test test");
		product.setAlias("test test");
		product.setShortDescription("Short description for test test");
		product.setFullDescription("Full description for test test");

		product.setBrand(brand);
		product.setCategory(category);

		product.setPrice(22000);
		product.setCost(20000);
		product.setEnabled(true);
		product.setInStock(true);

		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());

		Product savedProduct = repo.save(product);

		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllProducts() {

		Iterable<Product> listProducts = repo.findAll();
		listProducts.forEach(System.out::println);
	}

	@Test
	public void testGetProduct() {

		Integer id = 3;
		Product product = repo.findById(id).get();
		System.out.println(product);

		assertThat(product).isNotNull();
	}

	@Test
	public void testUpdateProduct() {

		Integer id = 3;
		Product product = repo.findById(id).get();
		product.setPrice(123456);

		repo.save(product);

		Product updatedProduct = entityManager.find(Product.class, id);

		assertThat(updatedProduct.getPrice()).isEqualTo(123456);
	}

	@Test
	public void testDeleteProduct() {

		Integer id = 3;
		repo.deleteById(id);

		Optional<Product> result = repo.findById(id);

		assertThat(!result.isPresent());
	}

	@Test
	public void testSaveProductWithImages() {
		Integer productId = 4;
		Product product = repo.findById(productId).get();

		product.setMainImage("main image.jpg");
		product.addExtraImage("extra image 1.png");
		product.addExtraImage("extra_image_2.png");
		product.addExtraImage("extra-image3.png");

		Product savedProduct = repo.save(product);

		assertThat(savedProduct.getImages().size()).isEqualTo(3);
	}

	@Test
	public void testSaveProductWithDetails() {
		Integer productId = 10;
		Product product = repo.findById(productId).get();

		product.addDetail("Device Memory", "128 GB");
		product.addDetail("CPU Model", "Snapdragon");
		product.addDetail("OS", "Android 11");

		Product savedProduct = repo.save(product);
		assertThat(savedProduct.getDetails()).isNotEmpty();
	}
}
