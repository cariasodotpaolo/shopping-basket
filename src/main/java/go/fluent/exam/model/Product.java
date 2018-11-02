package go.fluent.exam.model;

import go.fluent.exam.entity.ProductEntity;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String sku;
    private Double unitPrice;
    private String unit;

    public Product(Long id, String name, String sku, Double unitPrice, String unit) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.unit = unit;
    }

    public Product(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.sku = entity.getSku();
        this.unitPrice = entity.getUnitPrice();
        this.unit = entity.getUnit();
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
