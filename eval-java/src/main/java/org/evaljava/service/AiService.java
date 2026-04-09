package org.evaljava.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.evaljava.dao.RecipeDao;
import org.evaljava.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiService {

    protected final Client client;
    protected final RecipeDao recipeDao;

    public record AiResult(String text, Recipe recipe) {}

    public AiResult askGemini(String prompt) {

        List<Recipe> recipes = recipeDao.findAll();
        String formattedRecipes = recipes.stream()
                .map(r -> {
                    String tagsFormatted = r.getTags().stream()
                            .map(t -> t.getName())
                            .collect(Collectors.joining(","));
                    return r.getName() + "(" + tagsFormatted + ")";
                })
                .collect(Collectors.joining(" -"));

        String finalPrompt = "Parmi les recettes listées, trouve celle correspondant le plus à cette demande, " +
                "et répond comme si tu étais un serveur de restaurant '" +
                prompt + "' recettes : " + formattedRecipes;

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash-lite", finalPrompt, null);
        String responseText = response.text();

        Recipe matchedRecipe = recipes.stream()
                .filter(r -> responseText.toLowerCase().contains(r.getName().toLowerCase()))
                .findFirst()
                .orElse(null);

        return new AiResult(responseText, matchedRecipe);

    }
}
