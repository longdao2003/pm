package vn.humg.pm.service.Authenticate;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import vn.humg.pm.dto.request.AuthenticationRequest;
import vn.humg.pm.dto.request.IntrospectRequest;
import vn.humg.pm.dto.response.AuthenticationResponse;
import vn.humg.pm.dto.response.IntrospectResponse;
import vn.humg.pm.entity.User;
import vn.humg.pm.repository.UserRepository;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User u=userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại "));

        boolean result=passwordEncoder.matches(request.getPassword(), u.getPassword());
        if (!result){
            throw new RuntimeException("Unathorized") ;           
        }
        var token=generateToken(u.getUsername());
        return AuthenticationResponse.builder()
        .token(token)
        .authenticated(result)
        .build();
    }


    private String generateToken(String username){
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
        .subject(username)
        .issuer("http://localhost:8080")
        .issueTime(new Date())
        .expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
        .claim("customClaim", "Custom")
        .build();

        Payload payload=new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject=new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch(Exception e){
            log.error("Không tạo được token", e);
            throw new RuntimeException(e);
        }
        
    }


    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException,ParseException{
        var token=request.getToken();

        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT=SignedJWT.parse(token);

        var verified = signedJWT.verify(verifier);

        Date expireTime = signedJWT.getJWTClaimsSet().getExpirationTime();
       
        return IntrospectResponse.builder()
        .valid(verified && expireTime.after(new Date()))
        .build();


    }
    
}
