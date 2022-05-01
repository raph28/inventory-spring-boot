package com.ennea.inventory.Services;


import com.ennea.inventory.contollers.InventoryController;
import com.ennea.inventory.entity.ProductPage;
import com.ennea.inventory.entity.SupplierStats;
import com.ennea.inventory.repositories.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;

    private static final Logger logger= LoggerFactory.getLogger(InventoryService.class);

    public Map<String,Object> getBySupplierId(int supplierId,int page,int size){
        Pageable paging = PageRequest.of(page, size);
        Page<SupplierStats> inventoryPage= inventoryRepository.findBySupplier(supplierId,paging);
        Map<String, Object> response = new HashMap<>();
        response.put("stockByProduct",inventoryPage.getContent());
        response.put("currentPage", inventoryPage.getNumber());
        response.put("totalItems", inventoryPage.getTotalElements());
        response.put("totalPages", inventoryPage.getTotalPages());
        return response;
    }
    public Map<String,Object> getBySupplierIdAndName(int supplierId,String name,int page,int size){
        Long inventoryStock= inventoryRepository.findBySupplierAndProduct(supplierId,name);
        Map<String, Object> response = new HashMap<>();
        response.put("stockByProduct",inventoryStock);
        return response;
    }

    public Map<String,Object> getUnExpiredProducts(int page,int size){
        Pageable paging = PageRequest.of(page, size);
        Page<ProductPage> productPage= inventoryRepository.findByExpirationDate(new Date(),paging);
        Map<String, Object> response = new HashMap<>();
        response.put("stockByProduct",productPage.getContent());
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        return response;
    }
}
