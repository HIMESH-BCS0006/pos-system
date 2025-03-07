package com.SpringbootAcademy.pos2.dto.request;

//DTO ekak nisa no any extentions

public class CustomerUpdateDTO {
    private int id;
    private String customerName;
    private String customerAddress;
    private double customerSalary;

    public CustomerUpdateDTO() {
    }

    public CustomerUpdateDTO(int id, String customerName, String customerAddress, double customerSalary) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    @Override
    public String toString() {
        return "CustomerUpdateDTO{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                '}';
    }
}
