package com.example;

import com.example.Exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountServiceRestController {

    private final AccountServiceManager accountsManager = new AccountServiceManager();
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/create", produces = "application/json")
    public String createAccount(@RequestBody AccountDTO accountDTO) throws JsonProcessingException {
        boolean success = false;
        Map<String, Object> response = new HashMap<>();

        try{
            success = accountsManager.create(accountDTO.getName(), accountDTO.getEmailAddress(), accountDTO.getPassword());
        } catch (Exception e) {
            response.put("message", e.getMessage());
        }
        response.put("success", success);
        return mapper.writeValueAsString(response);
    }

    @PatchMapping(value = "/update", produces = "application/json")
    public String updateAccount(@RequestBody AccountDTO accountDTO) throws JsonProcessingException {
        boolean success = false;
        Map<String, Object> response = new HashMap<>();

        try{
            success = accountsManager.update(accountDTO.getName(), accountDTO.getEmailAddress(), accountDTO.getPassword());
        }catch (Exception e) {
            response.put("message", e.getMessage());
        }
        response.put("success", success);
        return mapper.writeValueAsString(response);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public String getAccount(@RequestParam String emailAddress, @RequestParam String password) throws JsonProcessingException {
        String response;
        try{
             response = accountsManager.fetch(emailAddress, password);
        }catch (Exception e){
            response = String.format("{ error %s , errorCode %d }", e.getMessage(), 500);
        }
        return response;
    }
}
