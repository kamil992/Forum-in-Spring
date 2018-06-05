package pl.kbieracki.forum.forum.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kbieracki.forum.forum.models.PostModel;

@Repository
public interface PostRepository extends CrudRepository<PostModel, Integer> {
}
