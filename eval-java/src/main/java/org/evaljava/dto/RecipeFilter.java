package org.evaljava.dto;

import java.util.List;

public record RecipeFilter(
        List<String> includedTags,
        List<String> excludedTags
) {}
