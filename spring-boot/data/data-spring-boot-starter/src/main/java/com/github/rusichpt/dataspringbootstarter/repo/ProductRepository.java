package com.github.rusichpt.dataspringbootstarter.repo;

import com.github.rusichpt.dataspringbootstarter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
