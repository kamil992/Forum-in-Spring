package pl.kbieracki.forum.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kbieracki.forum.forum.models.PostModel;
import pl.kbieracki.forum.forum.models.forms.PostForm;
import pl.kbieracki.forum.forum.models.repositories.PostRepository;
import pl.kbieracki.forum.forum.models.repositories.UserRepository;
import pl.kbieracki.forum.forum.models.services.UserService;

@Controller
public class PostController {

    final
    UserService userService;

    final
    PostRepository postRepository;

    final
    UserRepository userRepository;

    @Autowired
    public PostController(UserService userService, PostRepository postRepository, UserRepository userRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());

        return model;
    }

    @GetMapping("/addPost")
    public String getPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "addPost";
    }

    @PostMapping("/addPost")
    public String postPost(@ModelAttribute("postForm") PostForm postForm,
                           Model model){

        if(postForm.getTitle().isEmpty() || postForm.getText().isEmpty()){
            model.addAttribute("emptyFields", "You left empty fields!");
            return "addPost";
        }

        PostModel postModel = new PostModel(postForm);
        postModel.setUser(userService.getUser());
        postRepository.save(postModel);

        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("post", postRepository.findOne(id));
        return "post";
    }
}
