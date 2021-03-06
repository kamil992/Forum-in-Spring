package pl.kbieracki.forum.forum.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kbieracki.forum.forum.models.CategoryModel;
import pl.kbieracki.forum.forum.models.PostModel;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, Integer> {
    CategoryModel findByName(String name);
}
