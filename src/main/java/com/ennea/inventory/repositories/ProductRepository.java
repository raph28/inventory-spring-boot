package com.ennea.inventory.repositories;

import com.ennea.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Product findByCode(String code);
}
