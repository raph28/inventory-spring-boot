package com.ennea.inventory.entity;


import java.math.BigDecimal;

public class ProductPage {
    private String code;
    private String name;
    private BigDecimal mrp;

    public ProductPage(String code, String name, BigDecimal mrp, BigDecimal rate) {
        this.code = code;
        this.name = name;
        this.mrp = mrp;
        this.rate = rate;
    }

    private BigDecimal rate;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
