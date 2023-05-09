package com.cmoreno.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmoreno.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
}
