package com.watchstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {
	
	public Products() {}
	
    public Products(String productName, int price, int productCode, String productDescription, String productCategory) {
		super();
		this.productName = productName;
		this.price = price;
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment the product_id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "product_code", nullable = false)
    private int productCode;

    @Column(name = "product_description", nullable = false, length = 400)
    private String productDescription;

    @Column(name = "product_category", nullable = false, length = 10)
    private String productCategory;

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
   
    	
    
    }