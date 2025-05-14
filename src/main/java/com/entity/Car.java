package com.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "car_id")
    @SequenceGenerator(name = "car_id", initialValue = 100, allocationSize = 1)
    private int id;
    private String brand;
    private String model;
    private LocalDate registerDate;
    
    @OneToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    
    @OneToOne(mappedBy = "car")
    private Customer customer;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Engine getEngine() {
        return engine;
    }
    
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    
    public LocalDate getRegisterDate() {
        return registerDate;
    }
    
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}