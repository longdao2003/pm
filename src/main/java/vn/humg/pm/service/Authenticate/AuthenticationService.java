package vn.humg.pm.service.Authenticate;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

import vn.humg.pm.dto.request.AuthenticationRequest;
import vn.humg.pm.dto.request.IntrospectRequest;
import vn.humg.pm.dto.response.AuthenticationResponse;
import vn.humg.pm.dto.response.IntrospectResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException,ParseException;
}
