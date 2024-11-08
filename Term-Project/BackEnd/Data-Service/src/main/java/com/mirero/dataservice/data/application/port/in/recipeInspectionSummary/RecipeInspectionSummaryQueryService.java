package com.mirero.dataservice.data.application.port.in.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;

import java.util.UUID;

public interface RecipeInspectionSummaryQueryService {

    RecipeInspectionSummaryInfo getRecipeInspectionSummaryByEquipmentId(UUID equipmentId);
}
