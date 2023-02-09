package com.shophyol.admin.setting.state;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shophyol.common.entity.Country;
import com.shophyol.common.entity.State;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StateRepositoryTests {

	@Autowired
	private StateRepository repo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateStatesInTurkiye() {
		Integer countryId = 1;
		Country country = entityManager.find(Country.class, countryId);

		State state = repo.save(new State("Istanbul", country));

		assertThat(state).isNotNull();
		assertThat(state.getId()).isGreaterThan(0);
	}

	@Test
	public void testListStatesByCountry() {
		Integer countryId = 1;
		Country country = entityManager.find(Country.class, countryId);
		List<State> listStates = repo.findByCountryOrderByNameAsc(country);

		listStates.forEach(System.out::println);

		assertThat(listStates.size()).isGreaterThan(0);
	}

	@Test
	public void testUpdateState() {
		Integer stateId = 5;
		String stateName = "TESTTESTTSET";
		State state = repo.findById(stateId).get();

		state.setName(stateName);
		State updatedState = repo.save(state);

		assertThat(updatedState.getName()).isEqualTo(stateName);
	}

	@Test
	public void testGetState() {
		Integer stateId = 5;
		Optional<State> findById = repo.findById(stateId);
		assertThat(findById.isPresent());
	}

	@Test
	public void testDeleteState() {
		Integer stateId = 5;
		repo.deleteById(stateId);

		Optional<State> findById = repo.findById(stateId);
		assertThat(findById.isEmpty());
	}
}
