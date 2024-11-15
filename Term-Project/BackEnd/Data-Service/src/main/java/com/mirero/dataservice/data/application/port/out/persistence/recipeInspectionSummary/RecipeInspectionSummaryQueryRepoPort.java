package com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummaryEntity;

import java.util.UUID;

public interface RecipeInspectionSummaryQueryRepoPort {

    RecipeInspectionSummaryEntity findByEquipmentId(UUID equipmentId);
}
