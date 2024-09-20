package com.example;

public class AccountDTO {
    private String username;
    private String password;
    private String emailAddress;

    public AccountDTO(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getName(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmailAddress(){
        return emailAddress;
    }
}
