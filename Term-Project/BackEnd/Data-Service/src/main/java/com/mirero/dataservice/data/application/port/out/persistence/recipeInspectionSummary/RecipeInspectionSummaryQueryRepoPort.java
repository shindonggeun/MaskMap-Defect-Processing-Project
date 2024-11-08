package com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.RecipeInspectionSummary;

import java.util.UUID;

public interface RecipeInspectionSummaryQueryRepoPort {

    RecipeInspectionSummary findByEquipmentId(UUID equipmentId);
}