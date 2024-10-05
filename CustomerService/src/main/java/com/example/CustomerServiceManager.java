package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceManager {

    private final DataBaseConnector  dataBaseConnector = new DataBaseConnector();

    CustomerServiceManager(){}

    public Boolean register(CustomerDTO customerDTO) {
        String email = customerDTO.getEmail();
        String password = customerDTO.getPassword();
        String user_name = customerDTO.getUser_name();
        String first_name = customerDTO.getFirst_name();
        String last_name = customerDTO.getLast_name();
        String phone = customerDTO.getPhone();
        String address = customerDTO.getAddress();
        String account_number = customerDTO.account_number;

        String sql = "insert into customer values(?,?,?,?,?,?,?)";
        boolean success = false;
        try{
            success = dataBaseConnector.insert(sql);
        }catch (Exception e){}
        return success;
    }
    public Boolean authenticate(CustomerDTO customerDTO) {
        String email = customerDTO.getEmail();
        String password = customerDTO.getPassword();
        String sql = String.format("select * from customer where email=%s and password=%s", email, password);
        boolean success = false;
        try{
            String result = dataBaseConnector.get(sql);
            if(result != null){
                Gson gson = new Gson();
                Map<String, Object> resp = gson.fromJson(result, new TypeToken<HashMap<String, Object>>() {}.getType());
                success = resp.get("email").equals(email) && resp.get("password").equals(password);
            }
        }catch (Exception e){}
        return success;
    }

    public Boolean update(CustomerDTO customerDTO) {
        String email = customerDTO.getEmail();
        String password = customerDTO.getPassword();
        String user_name = customerDTO.getUser_name();
        String first_name = customerDTO.getFirst_name();
        String last_name = customerDTO.getLast_name();
        String phone = customerDTO.getPhone();
        String address = customerDTO.getAddress();

        String sql = "";
        boolean success = false;
        try{
            Map<String, Object> response = dataBaseConnector.patch(sql);
            success = (Boolean) response.get("success");
        }catch (Exception e){}
        return success;
    }
}
