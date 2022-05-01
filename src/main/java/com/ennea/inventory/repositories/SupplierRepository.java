package com.ennea.inventory.repositories;

import com.ennea.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
            @Query("SELECT s FROM Supplier s WHERE s.supplierName = :name" )
            Supplier findBySupplierName(@Param("name")String name);
}
