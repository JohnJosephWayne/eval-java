package org.evaljava.controller;

import org.evaljava.dao.QuestionHistoryDao;
import org.evaljava.dao.RecipeDao;
import org.evaljava.dto.RecipeAiCount;
import org.evaljava.dto.RecipeFilter;
import org.evaljava.model.Recipe;
import org.evaljava.model.Tag;
import org.evaljava.security.IsAdmin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@IsAdmin
public class RecipeController {

    protected final RecipeDao recipeDao;
    protected final QuestionHistoryDao questionHistoryDao;

    @GetMapping("/recipe/list")
    public List<Recipe> getAll() {
        return recipeDao.findAll();
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> get(@PathVariable int id) {
        Optional<Recipe> optionalRecipe = recipeDao.findById(id);

        if(optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalRecipe.get(), HttpStatus.OK);

    }

    @PostMapping("/recipe")
    public ResponseEntity<Recipe> create(@RequestBody @Valid Recipe recipe) {

        recipe.setId(null);

        recipeDao.save(recipe);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Recipe> optionalRecipe = recipeDao.findById(id);

        if(optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipeDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody Recipe recipe
    ) {
        recipe.setId(id);

        Optional<Recipe> optionalRecipe = recipeDao.findById(id);

        if(optionalRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipeDao.save(recipe);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/recipe/filter")
    public List<Recipe> filter(@RequestBody RecipeFilter filter){
        return recipeDao.findAll().stream()
                .filter(recipe -> {
                    Set<String> recipeTags = recipe.getTags().stream()
                            .map(Tag::getName)
                            .collect(Collectors.toSet());

                    boolean includesOk = filter.includedTags().isEmpty() ||
                            recipeTags.containsAll(filter.includedTags());

                    boolean excludesOk = filter.excludedTags().isEmpty() ||
                            filter.excludedTags().stream().noneMatch(recipeTags::contains);

                    return includesOk && excludesOk;
                })
                .collect(Collectors.toList());

    }

    @GetMapping("/recipe/stats")
    @IsAdmin
    public List<RecipeAiCount> getRecipeStats() {
        return questionHistoryDao.countByRecipe();
    }
}
