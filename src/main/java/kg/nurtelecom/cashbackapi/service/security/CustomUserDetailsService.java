package kg.nurtelecom.cashbackapi.service.security;

import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " loadUserByUsername");
        User user = this.userService.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
//
//
//    private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user){
//        String[] userRoles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
////        List<GrantedAuthority> authorities = new ArrayList<>();
////        user.getRoles().stream()
////                .map(role -> new SimpleGrantedAuthority(role.toString()))
////                .forEach(authorities::add);
//        return authorities;
//    }
}
