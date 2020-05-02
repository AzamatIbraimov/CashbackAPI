package kg.nurtelecom.cashbackapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean isCashier = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_CASHIER"));
        if (isAdmin) {
            return "redirect:/organization/list";
        } else if (isCashier){
            return "redirect:/cashier/";
        }
        return "redirect:/organization/forOrgAdmin";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "redirect:/";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}

