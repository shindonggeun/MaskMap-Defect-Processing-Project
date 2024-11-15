package com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummaryEntity;

public interface RecipeInspectionSummaryCommandRepoPort {

    RecipeInspectionSummaryEntity save(RecipeInspectionSummaryEntity recipeInspectionSummaryEntity);
}
