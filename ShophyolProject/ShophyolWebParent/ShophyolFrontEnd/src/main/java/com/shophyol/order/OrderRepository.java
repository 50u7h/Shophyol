package com.shophyol.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
