package com.ennea.inventory.repositories;

import com.ennea.inventory.entity.Product;
import com.ennea.inventory.entity.ProductPage;
import com.ennea.inventory.entity.SupplierStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ennea.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    @Query("SELECT new com.ennea.inventory.entity.SupplierStats(i.product.code,SUM(i.stock)) FROM Inventory i WHERE i.supplier.id = :supplierId GROUP BY i.product.code" )
    Page<SupplierStats> findBySupplier(int supplierId, Pageable pageable);

    @Query("SELECT SUM(i.stock) FROM Inventory i WHERE i.supplier.id = :supplierId and i.product.code=:productCode" )
    Long findBySupplierAndProduct(int supplierId,String productCode);

    @Query("SELECT DISTINCT new com.ennea.inventory.entity.ProductPage(i.product.code,i.product.name,i.product.mrp,i.product.rate) FROM Inventory i WHERE i.expDate>=:currentDate and i.stock>0")
    Page<ProductPage> findByExpirationDate(Date currentDate, Pageable pageable);
}
