package com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummary;

public interface RecipeInspectionSummaryCommandRepoPort {

    RecipeInspectionSummary save(RecipeInspectionSummary recipeInspectionSummary);
}
