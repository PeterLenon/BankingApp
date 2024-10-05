package com.example;

import java.util.HashMap;
import java.util.Map;

public class CustomerDTO {
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String address;
    public String account_number;

    CustomerDTO() {
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public Map<String, Object> customerInfo(){
        Map<String, Object> customer = new HashMap<>();
        customer.put("user_name", user_name);
        customer.put("password", password);
        customer.put("first_name", first_name);
        customer.put("last_name", last_name);
        customer.put("email", email);
        customer.put("phone", phone);
        customer.put("address", address);
        customer.put("account_number", account_number);
        return customer;
    }


}
