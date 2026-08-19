package com.example.repositories.jpa;

import com.example.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartJpaRepository extends JpaRepository<Cart, Long> {

}