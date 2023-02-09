package com.shophyol.admin.setting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
