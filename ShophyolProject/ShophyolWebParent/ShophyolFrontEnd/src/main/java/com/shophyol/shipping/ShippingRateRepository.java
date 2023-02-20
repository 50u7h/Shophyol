package com.shophyol.shipping;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Country;
import com.shophyol.common.entity.ShippingRate;

public interface ShippingRateRepository extends JpaRepository<ShippingRate, Integer> {

	public ShippingRate findByCountryAndState(Country country, String state);

}
