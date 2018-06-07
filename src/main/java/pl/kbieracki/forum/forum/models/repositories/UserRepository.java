package pl.kbieracki.forum.forum.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kbieracki.forum.forum.models.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    //Methods using to login:
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel> findByEmailAndPassword(String email, String password);



    //Methods using to register:
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}
