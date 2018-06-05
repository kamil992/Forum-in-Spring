package pl.kbieracki.forum.forum.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kbieracki.forum.forum.models.UserModel;
import pl.kbieracki.forum.forum.models.forms.RegisterForm;
import pl.kbieracki.forum.forum.models.repositories.UserRepository;
import pl.kbieracki.forum.forum.models.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {

    final
    UserService userService;

    final
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //method which allow to use user object i html files
    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());
        return model;
    }

    @GetMapping("/login")
    public String index(){
        return "login";
    }

    @GetMapping("/")
    public String dashboard(){
        return "dashboard";
    }


    //Login to forum:
    @PostMapping("/login")
    public String login(@RequestParam("password") String password,
                        @RequestParam("email") String email,
                        Model model){

        Optional<UserModel> userExists = userRepository.findByEmailAndPassword(email, password);
        if(userExists.isPresent()) {
            userService.setLogin(true);
            userService.setUser(userExists.get());
            //model.addAttribute("isLogin", userService.isLogin());
            return "redirect:/";
        }

       // model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("invalidInfo", "Incorrect email or password");
        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest requestHandler){
        userService.setLogin(false);
        requestHandler.changeSessionId();

        return "redirect:/";
    }

    //Registration to Forum:
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerForm") RegisterForm registerForm,
                               Model model){

        //check if password and repeat password are the same
        if(!registerForm.getRepeatPassword().equals(registerForm.getPassword())){
            model.addAttribute("isValidPassword", "Passwords are not the same!");
            return "register";
        }

        UserService.RegisterStatus registerStatus = userService.register(registerForm);

        if(registerStatus == UserService.RegisterStatus.BUSY_EMAIL){
            model.addAttribute("isValidEmail", "Account on this email adress exists!");
            return "register";
        }
        if(registerStatus == UserService.RegisterStatus.BUSY_LOGIN){
            model.addAttribute("isValidLogin", "This login is already busy!");
            return "register";
        }

        return"redirect:/";
    }

}
