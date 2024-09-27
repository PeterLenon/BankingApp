package com.example;


import java.util.Map;

public class AccountServiceManager {

    DataBaseConnector dataBaseConnector = new DataBaseConnector();

    public Boolean create(String user_name, String email_address, String password){
        boolean result = false;
        String sql = "";
        try{
            result = dataBaseConnector.insert(sql);
        }catch(Exception e){
            result = false;
        }
        return result;
    }

    public Boolean update(String user_name, String email_address, String password){
        boolean success = false;
        String sql = "";
        try{
            Map<String,Object> result = dataBaseConnector.patch(sql);
            if(result != null) {
                success = (boolean) result.get("success");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return success;
    }

    public String fetch(String email_address, String password){
        String sql = "";
        String result;
        try{
            result = dataBaseConnector.get(sql);
        }catch(Exception e){
            result = String.format("{ error: %s }", e.getMessage());
        }
        return result;
    }
}
