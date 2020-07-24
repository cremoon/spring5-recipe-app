package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by maikbartels on 2020.07.23
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Each");
        }
        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Tablespoon");
        }
        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Teaspoon");
        }
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Dash");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Pint");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found - Cup");
        }

        // get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found - American");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found - Mexican");
        }

        // Categories - get optionals
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Guacamole
        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");
        recipe.setPrepTime(10);
        recipe.setCookTime(0);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setDirections("1. ...\n2. ...");

        Notes notes = new Notes();
        notes.setRecipeNotes("For a very quick .....");
        recipe.setNotes(notes);

        recipe.addIngredient(new Ingredient("First", new BigDecimal(2), eachUom));
        recipe.addIngredient(new Ingredient("Second", new BigDecimal(5), teaspoonUom));
        recipe.addIngredient(new Ingredient("Second", new BigDecimal(2),tablespoonUom));

        recipe.getCategories().add(americanCategory);
        recipe.getCategories().add(mexicanCategory);

        recipes.add(recipe);

        return recipes;
    }
}
