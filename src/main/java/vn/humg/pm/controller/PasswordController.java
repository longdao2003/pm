package vn.humg.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.humg.pm.dto.request.PasswordRequest;
import vn.humg.pm.dto.response.ApiResponse;
import vn.humg.pm.dto.response.PasswordResponse;

import vn.humg.pm.service.Password.PasswordService;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    PasswordService passwordService;
    @PostMapping("")
    public ApiResponse<PasswordResponse> create(@RequestBody PasswordRequest request) {
        return ApiResponse.<PasswordResponse>builder()
                .result(passwordService.createPassword(request))
                .code(200)   
                .build();    
            }


    @GetMapping("/{id}")
    public ApiResponse<PasswordResponse>getById(@PathVariable Integer id) {
        return ApiResponse.<PasswordResponse>builder()
        .result(passwordService.getPasswordById(id))
        .code(200)   
        .build();    
    }


    @PutMapping("/{id}")
    public ApiResponse<PasswordResponse>update(@PathVariable Integer id, @RequestBody PasswordRequest password) {

        return ApiResponse.<PasswordResponse>builder()
        .result(passwordService.editPassword(id, password))
        .code(200)   
        .build();  
    }

    @DeleteMapping("/{id}")                                                                                                                                                                                                                                                             
    public ApiResponse<PasswordResponse> delete(@PathVariable Integer id) {
        return ApiResponse.<PasswordResponse>                                                                                                                                                       builder()
        .result(passwordService.deletePassword(id))
        .code(200)                          
        .build();  
    }
}
