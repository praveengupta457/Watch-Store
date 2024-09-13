package com.watchstore.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {
    public Admin() {}
    
    public Admin(String name, String phone, String emailId, String password, Date date) {
		super();
		this.name = name;
		this.phone = phone;
		this.emailId = emailId;
		this.password = password;
		this.date = date;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment the ID
    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "email_id", nullable = false, length = 50)
    private String emailId;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
