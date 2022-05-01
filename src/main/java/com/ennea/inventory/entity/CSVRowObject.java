package com.ennea.inventory.entity;

import java.math.BigDecimal;

import java.util.Date;

public class CSVRowObject {
    private String code;
    private String name;
    private String batch;
    private Integer stock;
    private Integer deal;
    private Integer free;
    private BigDecimal mrp;
    private BigDecimal rate;
    private Date exp;
    private String company;
    private String supplier;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public CSVRowObject() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }


    public void setExp(Date exp) {
        this.exp = exp;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Date getExp() {
        return exp;
    }

    public String getCompany() {
        return company;
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

    public String getSupplier() {
        return supplier;
    }
}
