package springwebsell.websell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springwebsell.websell.dto.AdminDTO;
import springwebsell.websell.model.Admin;
import springwebsell.websell.service.impl.AdminService;

import javax.validation.Valid;

@Controller
public class AdminRegisterController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login admin");
        return "login";
    }



//    @GetMapping("/index")
//    public String ViewAdminHomePage(Model model){
//        model.addAttribute("title", "Admin home page");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
//        return "index";
//    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("admin", new AdminDTO());
        model.addAttribute("title", "Admin register");
        return "register";
    }

    @GetMapping("forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title", "Forgot password");
        return "forgot-password";
    }

    @PostMapping("/register")
    public String addNewAdmin(@Valid @ModelAttribute("admin")AdminDTO adminDto,
                              BindingResult result,
                              Model model){

        try {

            if(result.hasErrors()){
                model.addAttribute("admin", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if(admin != null){
                model.addAttribute("admin", adminDto);
                System.out.println("admin not null");
                model.addAttribute("emailError", "Your email has been registered!");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("success", "Register successfully!");
                model.addAttribute("admin", adminDto);
            }else{
                model.addAttribute("admin", adminDto);
                model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
                System.out.println("password not same");
                return "register";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";

    }
}
