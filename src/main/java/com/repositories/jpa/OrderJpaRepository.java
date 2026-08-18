package com.repositories.jpa;
import com.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}