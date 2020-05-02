package kg.nurtelecom.cashbackapi;

import kg.nurtelecom.cashbackapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CashbackapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashbackapiApplication.class, args);
        init();
    }

    public static void init(){
    }

    @Autowired
    private UserRepository userRepository;

//    @PostConstruct
//    private void postConstruct() {
//        User admin = new User(null, null, "adminuser", "admin123", true);
//        userRepository.save(admin, normalUser);
//    }
}

