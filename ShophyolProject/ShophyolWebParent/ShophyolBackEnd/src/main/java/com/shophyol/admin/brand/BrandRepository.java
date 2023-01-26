package com.shophyol.admin.brand;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

	public Long countById(Integer id);

	public Brand findByName(String name);

}
