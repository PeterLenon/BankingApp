package com.example;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerServiceRestController {

    private final CustomerServiceManager customerServiceManager = new CustomerServiceManager();

    @PostMapping(value = "/register", produces = "application/json")
    public String register(@RequestBody CustomerDTO customerDTO) {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        try{
            success = customerServiceManager.register(customerDTO);
        }catch (Exception e){
            response.put("error", e.getMessage());
        }
        response.put("success", success);
        return response.toString();
    }

    @PostMapping(value="authenticate", produces = "application/json")
    public String authenticate(@RequestBody CustomerDTO customerDTO) {
        boolean auth = false;
        Map<String, Object> response = new HashMap<>();
        try{
            auth = customerServiceManager.authenticate(customerDTO);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        response.put("success", auth);
        return response.toString();
    }

    @PatchMapping(value= "update", produces = "application/json")
    public String update(@RequestBody CustomerDTO customerDTO) {
        boolean success = false;
        Map<String, Object> response = new HashMap<>();
        try{
            success = customerServiceManager.update(customerDTO);
        }catch (Exception e){
            response.put("error", e.getMessage());
        }
        response.put("success", success);
        return response.toString();
    }
}
