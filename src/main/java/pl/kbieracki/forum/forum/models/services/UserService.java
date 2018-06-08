package pl.kbieracki.forum.forum.models.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kbieracki.forum.forum.models.UserModel;
import pl.kbieracki.forum.forum.models.UserType;
import pl.kbieracki.forum.forum.models.forms.RegisterForm;
import pl.kbieracki.forum.forum.models.repositories.UserRepository;

@Service
public class UserService {
/*
 This service is using to registration new user to database
 */
    public enum RegisterStatus{
        OK,
        BUSY_LOGIN,
        BUSY_EMAIL;
    }

    @Getter @Setter
    private boolean isLogin;

    @Getter @Setter
    private UserModel user;

    final
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterStatus register(RegisterForm registerForm){
        //Chceck if email busy

        if(userRepository.existsByEmail(registerForm.getEmail())){
            return RegisterStatus.BUSY_EMAIL;
        }
        //Chceck if login busy
        if(userRepository.existsByLogin(registerForm.getLogin())){
            return RegisterStatus.BUSY_LOGIN;
        }
        UserModel newUser = new UserModel(registerForm);
        newUser.setUserType(UserType.USER);

        //save new user
        userRepository.save(newUser);

        return RegisterStatus.OK;
    }
}
