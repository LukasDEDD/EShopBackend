package com.repositories.jpa;

import com.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartJpaRepository extends JpaRepository<Cart, Long> {

}