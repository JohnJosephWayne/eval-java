package org.evaljava.dao;

import org.evaljava.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDao extends JpaRepository<Recipe,Integer> {

//    @Query("SELECT new org.evaljava.dao.RecipeDao.RecipeCount(r.name, count(*)) " +
//            "FROM Recipe r " +
//            "JOIN r.tags t")
//    RecipeCount[] countTagByRecipe();
//
//    @Query("SELECT count(*) " +
//            "FROM Recipe r " +
//            "JOIN r.tags t " +
//            "WHERE r.name = :name")
//    int countTagByRecipeName(@Param("name") String name);
//
//    record RecipeCount (String name, long count){}
}
