package vn.humg.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    // @GetMapping("/{id}")
    // public ResponseEntity<ApiResponse<PasswordResponse>> getById(@PathVariable Integer id) {
    //     PasswordResponse response = passwordService.getById(id);
    //     return ResponseEntity.ok(new ApiResponse<>(true, "Password found", response));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<ApiResponse<PasswordResponse>> update(@PathVariable Integer id, @RequestBody Password password) {
    //     PasswordResponse response = passwordService.update(id, password);
    //     return ResponseEntity.ok(new ApiResponse<>(true, "Password updated successfully", response));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
    //     passwordService.delete(id);
    //     return ResponseEntity.ok(new ApiResponse<>(true, "Password deleted successfully", null));
    // }
}
