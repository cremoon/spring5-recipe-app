package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by maikbartels on 2020.07.23
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
