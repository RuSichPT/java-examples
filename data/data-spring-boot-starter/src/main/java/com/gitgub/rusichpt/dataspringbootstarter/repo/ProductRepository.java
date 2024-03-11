package com.gitgub.rusichpt.dataspringbootstarter.repo;

import com.gitgub.rusichpt.dataspringbootstarter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
