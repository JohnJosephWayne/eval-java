package org.evaljava.dao;

import org.evaljava.dto.RecipeAiCount;
import org.evaljava.model.AppUser;
import org.evaljava.model.QuestionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionHistoryDao extends JpaRepository<QuestionHistory, Integer> {

    List<QuestionHistory> findByUser(AppUser user);

    @Query("select RecipeAiCount(r.name, count(qh)) FROM QuestionHistory qh join qh.recipe r group by r.name")
    List<RecipeAiCount> countByRecipe();
}
