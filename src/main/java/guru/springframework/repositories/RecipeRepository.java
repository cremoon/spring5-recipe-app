package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by maikbartels on 2020.07.23
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
