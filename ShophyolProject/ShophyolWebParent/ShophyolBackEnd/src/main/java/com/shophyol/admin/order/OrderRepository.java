package com.shophyol.admin.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
