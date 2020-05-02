package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable("userName") String name) {
        return userService.findByUsername(name);
    }


    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.putById(id, user);
    }

    @PostMapping()
    public User postUser(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

//    @PostMapping("/createManager")
//    public User create (@RequestBody OrganizationManagerDto organizationManagerDto){
//        return userService.createOrganizationManager(organizationManagerDto);
//    }

}

