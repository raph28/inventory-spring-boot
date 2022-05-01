package com.ennea.inventory.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "supplier")
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE)
public class Supplier {
    @Id
    //@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = false)
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "company_name")
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Inventory> iList;

    public Supplier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
