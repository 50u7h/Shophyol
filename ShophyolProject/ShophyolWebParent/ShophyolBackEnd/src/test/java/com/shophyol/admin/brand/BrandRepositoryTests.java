package com.shophyol.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shophyol.common.entity.Brand;
import com.shophyol.common.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandRepositoryTests {

	@Autowired
	private BrandRepository repo;

	@Test
	public void testCreateBrand1() {
		Category phones = new Category(23);
		Brand huawei = new Brand("Huawei");
		huawei.getCategories().add(phones);

		Brand savedBrand = repo.save(huawei);

		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateBrand2() {
		Category cpus = new Category(4);

		Brand intel = new Brand("Intel");
		intel.getCategories().add(cpus);

		Brand savedBrand = repo.save(intel);

		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateBrand3() {
		Category gpus = new Category(5);

		Brand nvidia = new Brand("Nvidia");
		nvidia.getCategories().add(gpus);

		Brand savedBrand = repo.save(nvidia);

		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateBrand4() {
		Category laptops = new Category(22);

		Brand msi = new Brand("test");
		msi.getCategories().add(laptops);

		Brand savedBrand = repo.save(msi);

		assertThat(savedBrand).isNotNull();
		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testFindAll() {
		Iterable<Brand> brands = repo.findAll();
		brands.forEach(System.out::println);

		assertThat(brands).isNotEmpty();
	}

	@Test
	public void testGetById() {
		Brand brand = repo.findById(1).get();

		assertThat(brand.getName()).isEqualTo("Xiaomi");
	}

	@Test
	public void testUpdateName() {
		String newName = "qwerty";
		Brand test = repo.findById(8).get();
		test.setName(newName);

		Brand savedBrand = repo.save(test);
		assertThat(savedBrand.getName()).isEqualTo(newName);
	}

	@Test
	public void testDelete() {
		Integer id = 8;
		repo.deleteById(id);

		Optional<Brand> result = repo.findById(id);

		assertThat(result.isEmpty());
	}

}
