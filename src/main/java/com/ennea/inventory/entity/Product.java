package com.ennea.inventory.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product {
    @Id
    @Column(name = "product_code")
    private String code;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_mrp")
    private BigDecimal mrp;

    @Column(name = "product_rate")
    private BigDecimal rate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_code")
    private Set<Inventory> inventoryList;

    public Product() {
    }

    public Product(String code, String name, BigDecimal mrp, BigDecimal rate) {
        this.code = code;
        this.name = name;
        this.mrp = mrp;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
}
