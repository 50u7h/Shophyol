package com.shophyol.admin.setting.state;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shophyol.common.entity.Country;
import com.shophyol.common.entity.State;

public interface StateRepository extends CrudRepository<State, Integer> {

	public List<State> findByCountryOrderByNameAsc(Country country);

}
