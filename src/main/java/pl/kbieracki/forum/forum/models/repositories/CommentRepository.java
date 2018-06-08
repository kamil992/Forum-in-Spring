package pl.kbieracki.forum.forum.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kbieracki.forum.forum.models.CommentModel;

public interface CommentRepository extends CrudRepository<CommentModel, Integer> {

}
