package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.RecipeInspectionSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeInspectionSummaryQueryRepository extends JpaRepository<RecipeInspectionSummary, UUID> {

    RecipeInspectionSummary findByEquipmentId(UUID equipmentId);
}
