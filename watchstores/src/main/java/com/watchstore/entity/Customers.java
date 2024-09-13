package com.watchstore.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customers {
	
	public Customers(){}
	
    public Customers(String name, String emailId, String phone, String address, String password, Date date) {
		
    	super();
		this.name = name;
		this.emailId = emailId;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.date = date;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will Auto-increment the customer_id
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email_id", nullable = false, length = 50)
    private String emailId;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
