package org.evaljava.unit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.evaljava.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class RecipeUnitTest {

    static Validator validator;

    @BeforeAll
    public static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void recipeWithBlankName_shouldNotBeValid() {
        Recipe recipe = new Recipe();
        recipe.setName("");

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        boolean violationExists = violations.stream()
                .anyMatch(c -> c.getPropertyPath().toString().equals("name")
                        && c.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName().equals("NotBlank"));

        Assertions.assertTrue(violationExists);
    }

    @Test
    public void recipeWithNullName_shouldNotBeValid() {
        Recipe recipe = new Recipe();
        recipe.setName(null);

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        boolean violationExists = violations.stream()
                .anyMatch(c -> c.getPropertyPath().toString().equals("name"));

        Assertions.assertTrue(violationExists);
    }

    @Test
    public void recipeWithValidName_shouldBeValid() {
        Recipe recipe = new Recipe();
        recipe.setName("Pasta Carbonara");

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        boolean nameHasViolation = violations.stream()
                .anyMatch(c -> c.getPropertyPath().toString().equals("name"));

        Assertions.assertFalse(nameHasViolation);
    }

}
