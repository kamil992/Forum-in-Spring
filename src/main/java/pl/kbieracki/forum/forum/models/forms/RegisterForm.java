package pl.kbieracki.forum.forum.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterForm {

    private int id;
    private String login;
    private String password;
    private String repeatPassword;
    private String email;
}
