package vn.humg.pm.config;


import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.experimental.NonFinal;


@Configuration
public class SecurityConfig {


    private final String[] PUBLIC_ENDPOINTS={"api/user","api/auth/token","api/auth/introspect"
    ,"/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-ui.html",
    "/webjars/**"};
    
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
            .requestMatchers(HttpMethod.POST, "api/user", "api/auth/token", "api/auth/introspect")
            .permitAll()
            .requestMatchers(PUBLIC_ENDPOINTS)
            .permitAll()
            .anyRequest()
            .authenticated()
    );

    http.oauth2ResourceServer(oath2 -> oath2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
    }


    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec=new SecretKeySpec(SIGNER_KEY.getBytes(),"HSS12");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
