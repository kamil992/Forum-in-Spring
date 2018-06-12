package pl.kbieracki.forum.forum.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kbieracki.forum.forum.models.PostRatingModel;

public interface RatingRepository extends CrudRepository<PostRatingModel, Integer> {
    boolean existsByUserIdAndPostId(int userId, int postId);
}
