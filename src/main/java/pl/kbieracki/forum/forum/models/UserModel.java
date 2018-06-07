package pl.kbieracki.forum.forum.models;

import lombok.Data;
import lombok.NoArgsConstructor;


import org.hibernate.validator.constraints.Email;
import pl.kbieracki.forum.forum.models.forms.RegisterForm;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Table(name = "user_forum")
@Entity
@Data
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @NotNull
    private String login;

    @NotNull
    private String password;


   @NotNull
   @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    public UserModel(RegisterForm registerForm){
        login = registerForm.getLogin();
        password = registerForm.getPassword();
        email = registerForm.getEmail();
    }
}
