package org.evaljava.mock;

import org.evaljava.dao.RecipeDao;
import org.evaljava.model.Recipe;
import org.evaljava.model.Tag;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockRecipeDao implements RecipeDao {

    public static final int EXISTING_ID = 1;

    private Recipe buildRecipe(int id, String name, List<Tag> tags) {
        Recipe r = new Recipe();
        r.setId(id);
        r.setName(name);
        r.setTags(tags);
        return r;
    }
    @Override
    public Optional<Recipe> findById(Integer id) {
        if (id == EXISTING_ID) {
            return Optional.of(buildRecipe(EXISTING_ID, "Pasta", List.of()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public <S extends Recipe> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Recipe> findAll() {
    Tag italian = new Tag(); italian.setId(1); italian.setName("italian");
    Tag vegan   = new Tag(); vegan.setId(2);   vegan.setName("vegan");

    return List.of(
        buildRecipe(1, "Pasta",  List.of(italian)),
        buildRecipe(2, "Salad",  List.of(vegan)),
        buildRecipe(3, "Pizza",  List.of(italian, vegan))
        );
    }

    @Override
    public List<Recipe> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends Recipe> S save(S entity) {
    return entity;
    }

 // ── méthodes JpaRepository non utilisées ────────────────────────────────

    @Override
    public void deleteById(Integer id) {}

    @Override
    public void delete(Recipe entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Recipe> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Recipe> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Recipe> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Recipe> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Recipe getOne(Integer integer) {
        return null;
    }

    @Override
    public Recipe getById(Integer integer) {
        return null;
    }

    @Override
    public Recipe getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Recipe> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Recipe> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Recipe> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Recipe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Recipe> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Recipe> findAll(Pageable pageable) {
        return null;
    }
}
