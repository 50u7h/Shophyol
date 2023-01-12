package com.shophyol.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.shophyol.common.entity.Role;
import com.shophyol.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userAdmin = new User("samet@guney1.com", "123456", "Samet", "Guney");
		userAdmin.addRole(roleAdmin);

		User savedUser = repo.save(userAdmin);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userTest = new User("test@test.com", "123456", "test", "test");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userTest.addRole(roleEditor);
		userTest.addRole(roleAssistant);

		User savedUser = repo.save(userTest);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userTest = repo.findById(1).get();
		System.out.println(userTest);
		assertThat(userTest).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User userTest = repo.findById(2).get();
		userTest.setEnabled(true);
		userTest.setEmail("qwerty@asdfg.com");

		repo.save(userTest);
	}

	@Test
	public void testUpdateUserRoles() {
		User userTest = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);

		userTest.getRoles().remove(roleEditor);
		userTest.addRole(roleSalesperson);

		repo.save(userTest);
	}

	@Test
	public void testDeleteUser() {
		Integer userId = 4;
		repo.deleteById(userId);

	}

	@Test
	public void testGetUserByEmail() {
		String email = "test@test.com";
		User user = repo.getUserByEmail(email);

		assertThat(user).isNotNull();
	}

	@Test
	public void testCountById() {
		Integer id = 11;
		Long countByIdLong = repo.countById(id);
		System.out.println(countByIdLong);

		assertThat(countByIdLong).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testStatus() {
		Integer id = 11;
		boolean status = repo.findById(id).get().isEnabled();
		repo.updateEnabledStatus(id, !status);

	}

	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);

		List<User> listUsers = page.getContent();

		listUsers.forEach(user -> System.out.println(user));

		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
}
