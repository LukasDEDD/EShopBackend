package com.example.repositories.jpa;
import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}