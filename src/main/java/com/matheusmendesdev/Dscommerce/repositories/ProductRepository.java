package com.matheusmendesdev.Dscommerce.repositories;

import com.matheusmendesdev.Dscommerce.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
<<<<<<< HEAD
	@Query("SELECT obj FROM Product obj " +
			"WHERE UPPER(obj.name) " +
			"LIKE UPPER(CONCAT('%', :name, '%'))")
=======
	@Query("SELECT obj FROM Product obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
>>>>>>> daeff5e3a74b9f34ce245280bb81afdb708e7cb2
	Page<Product> searchByName(String name, Pageable pageable);
}
