package com.ennea.inventory.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "batch_code")
    private String batch;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "deal")
    private Integer deal;
    @Column(name = "free")
    private Integer free;
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expDate;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Inventory() {
    }

    public Inventory(String batch, Integer stock, Integer deal, Integer free, Date expDate, Product product, Supplier supplier) {
        this.batch = batch;
        this.stock = stock;
        this.deal = deal;
        this.free = free;
        this.expDate = expDate;
        this.product = product;
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // getters and setters...
}
