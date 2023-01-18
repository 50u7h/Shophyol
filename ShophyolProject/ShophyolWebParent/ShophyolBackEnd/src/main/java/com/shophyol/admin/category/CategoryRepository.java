package com.shophyol.admin.category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
