package vn.humg.pm.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import vn.humg.pm.dto.request.AuthenticationRequest;
import vn.humg.pm.dto.request.IntrospectRequest;
import vn.humg.pm.dto.response.ApiResponse;
import vn.humg.pm.dto.response.AuthenticationResponse;
import vn.humg.pm.dto.response.IntrospectResponse;
import vn.humg.pm.service.Authenticate.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result=authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
        .result(result
        )
        .code(200)
        .build();
    }


    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws JOSEException,ParseException {
        var result=authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
        .result(result)
        .code(200)
        .build();
    }
}
