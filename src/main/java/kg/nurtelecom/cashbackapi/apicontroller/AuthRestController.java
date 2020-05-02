package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.service.AuthService;
import kg.nurtelecom.cashbackapi.service.security.jwt.JwtResponse;
import kg.nurtelecom.cashbackapi.service.security.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtUser form) {

        return authService.login(form);
    }


//    @RequestMapping(value = "/reg")
//    public String registration(@RequestBody JwtUser form) {
//
//        ClientDevice user = clientDeviceService.findByPhone(form.getUsername());
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User with username: " + form.getUsername() + " not found");
//        }
//        if (form.getUsername().equals(user.getPhoneNumber()) && form.getPassword().equals(user.getPassword())) //or you can use your service to authenticate user
//        {
//            return jwtTokenUtil.generateToken(form.getUsername());
//        } else {
//            return "INVALID CREDENTIAL";
//        }
//    }
}
