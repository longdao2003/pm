package vn.humg.pm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.humg.pm.dto.request.UserRequest;
import vn.humg.pm.dto.response.ApiResponse;
import vn.humg.pm.dto.response.UserResponse;

import vn.humg.pm.service.UserService.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping("")
    public ApiResponse<UserResponse> postUser(@RequestBody UserRequest userRequest) {

        return ApiResponse.<UserResponse>builder()
        .result(userService.createUser(userRequest))
        .code(200)   
        .build();
    }
    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserInfo())
                .build();
    }
    
    

    
    
}
