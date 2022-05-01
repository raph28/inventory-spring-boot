package com.ennea.inventory.entity;

public class SupplierStats {
    String productCode;
    long stock;

    public SupplierStats(String productCode, long stock) {
        this.productCode = productCode;
        this.stock = stock;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
