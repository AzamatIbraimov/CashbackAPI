package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.service.security.jwt.JwtResponse;
import kg.nurtelecom.cashbackapi.service.security.jwt.JwtUser;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<JwtResponse> login(JwtUser form);


}
