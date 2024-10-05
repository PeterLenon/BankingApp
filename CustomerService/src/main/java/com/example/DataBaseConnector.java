package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseConnector {
    private String username= "Peter";
    private String password = "some_password";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private Connection connection;


    private Boolean connect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private Boolean disconnect(){
        try{
            connection.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String get(String sql){
        Map<String ,Object> result = new HashMap<>();

        try{
            connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()){
                for(int col = 1; col <= columnCount; col++){
                    result.put(metaData.getColumnName(col), resultSet.getString(col));
                }
            }
            disconnect();
        }catch (Exception e){
            result.put("error", e.getMessage());
        }
        return result.toString();
    }

    public Map<String, Object> patch(String sql){
        Map<String ,Object> result = new HashMap<>();

        try{
            connect();
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            if(affectedRows > 0){
                result.put("success", true);
                result.put("code", "success");
            }

        }catch(Exception e){
            result.put("error", e.getMessage());
            result.put("success", false);
        }
        return result;
    }

    public Boolean insert(String sql){
        boolean success = false;
        try{
            connect();
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            if(rowsAffected > 0){
                success = true;
            }

        }catch(Exception e){
        }
        return success;
    }

    public String createAccountNumber(){
        int accountNumberOffSet = 3;
        int accountNumberLength = 16;
        String sql = "get last account number";
        String result = this.get(sql);
        Gson gson = new Gson();
        Map<String, Object> res = gson.fromJson(result, new TypeToken<HashMap<String, Object>>() {}.getType());
        double lastAccountNumber = Double.parseDouble( (String) res.get("accountNumber")) + accountNumberOffSet;
        return  String.valueOf(lastAccountNumber).substring(0, accountNumberLength);
    }
}
