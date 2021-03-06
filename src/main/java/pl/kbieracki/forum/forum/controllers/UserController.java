package pl.kbieracki.forum.forum.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kbieracki.forum.forum.models.UserModel;
import pl.kbieracki.forum.forum.models.forms.RegisterForm;
import pl.kbieracki.forum.forum.models.repositories.CategoryRepository;
import pl.kbieracki.forum.forum.models.repositories.PostRepository;
import pl.kbieracki.forum.forum.models.repositories.UserRepository;
import pl.kbieracki.forum.forum.models.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Controller
public class UserController {

    final
    UserService userService;

    final
    UserRepository userRepository;

    final
    PostRepository postRepository;

    final
    CategoryRepository categoryRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, PostRepository postRepository, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    //method which allow to use user object i html files
    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());
        model.addAttribute("categories", categoryRepository.findAll());
        return model;
    }

    @GetMapping("/login")
    public String index(Model model){
        model.addAttribute("post", postRepository.findAllByOrderByIdDesc());
        return "login";
    }

    @GetMapping("/")
    public String dashboard(Model model){
        model.addAttribute("post", postRepository.findAllByOrderByIdDesc());
        return "dashboard";
    }

    @GetMapping("/category/{category}")
    public String index(Model model,
                        @PathVariable("category") String category){
        model.addAttribute("post", categoryRepository.findByName(category).getPostList());

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
