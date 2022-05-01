package com.ennea.inventory.Services;

import com.ennea.inventory.Util.FileParser;
import com.ennea.inventory.contollers.InventoryController;
import com.ennea.inventory.entity.CSVRowObject;
import com.ennea.inventory.entity.Inventory;
import com.ennea.inventory.entity.Product;
import com.ennea.inventory.entity.Supplier;
import com.ennea.inventory.repositories.InventoryRepository;
import com.ennea.inventory.repositories.ProductRepository;
import com.ennea.inventory.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryFileUploadServiceImpl implements FileUploadService {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    FileParser fileParser;

    private static final Logger logger= LoggerFactory.getLogger(InventoryFileUploadServiceImpl.class);

    @Override
    public int processFile(InputStream inputStream){
        List<CSVRowObject> csvRowObjectList= mapRowsToObjects(inputStream);
        List<Inventory> inventoryList= mapCSVRowsToInventory(csvRowObjectList);
        return inventoryList.size();
    }

    public List<Inventory> mapCSVRowsToInventory(List<CSVRowObject> csvRowObjectList){
        List<Inventory> inventoryList=new ArrayList<>();
        try{
            for(CSVRowObject csvRowObject:csvRowObjectList){
                Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findByCode(csvRowObject.getCode()));
                Product newproduct=null;
                if(!optionalProduct.isPresent()) {
                    newproduct= new Product(csvRowObject.getCode(),
                            csvRowObject.getName(),
                            csvRowObject.getMrp(),
                            csvRowObject.getRate());
                    productRepository.save(newproduct);
                }
                else{
                    newproduct=optionalProduct.get();
                }
                Optional<Supplier> optionalSupplier = Optional.ofNullable(supplierRepository.findBySupplierName(csvRowObject.getSupplier()));
                Supplier currentSupplier;
                if(!optionalSupplier.isPresent()){
                    Supplier supplier =new Supplier();
                    supplier.setSupplierName(csvRowObject.getSupplier());
                    supplier.setCompanyName(csvRowObject.getCompany());
                    supplierRepository.save(supplier);
                    currentSupplier=supplier;
                }
                else{
                    currentSupplier= optionalSupplier.get();
                }
                Inventory inventory=new Inventory(csvRowObject.getBatch(),
                        csvRowObject.getStock(),
                        csvRowObject.getDeal(),
                        csvRowObject.getFree(),
                        csvRowObject.getExp(),
                        newproduct,
                        currentSupplier);
                inventoryRepository.save(inventory);
                inventoryList.add(inventory);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return inventoryList;
    }
    public List<CSVRowObject> mapRowsToObjects(InputStream inputStream){
        List<CSVRowObject> csvRowObjectList=fileParser.parse(inputStream);
        return  csvRowObjectList;
    }

}
