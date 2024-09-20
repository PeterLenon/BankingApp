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
        return handleAccountOperation(() -> accountsManager.create(accountDTO.getName(), accountDTO.getEmailAddress(), accountDTO.getPassword()),
            "Account created successfully",
            "Email address already taken");
    }

    @PatchMapping(value = "/update", produces = "application/json")
    public String updateAccount(@RequestBody AccountDTO accountDTO) throws JsonProcessingException {
        return handleAccountOperation(() -> accountsManager.update(accountDTO.getName(), accountDTO.getEmailAddress(), accountDTO.getPassword()),
            "Account updated successfully",
            "Error updating account");
    }

    @GetMapping(value = "/get", produces = "application/json")
    public String getAccount(@RequestParam String emailAddress, @RequestParam String password) throws JsonProcessingException {
        return handleAccountOperation(() -> accountsManager.fetch(emailAddress, password),
            "Account retrieved successfully",
            "User not found");
    }

    private String handleAccountOperation(AccountOperation operation, String successMessage, String errorMessage) throws JsonProcessingException {
        Map<String, Object> responseJson = new HashMap<>();
        boolean success;
        try {
            success = operation.execute();
        } catch (Exception ex) {
            success = false;
            errorMessage = ex.getMessage() != null ? ex.getMessage() : errorMessage;
        }
        responseJson.put("success", success);
        responseJson.put("message", success ? successMessage : errorMessage);
        responseJson.put("code", success ? 200 : 400);
        return mapper.writeValueAsString(responseJson);
    }

    @FunctionalInterface
    private interface AccountOperation {
        boolean execute() throws Exception;
    }
}
