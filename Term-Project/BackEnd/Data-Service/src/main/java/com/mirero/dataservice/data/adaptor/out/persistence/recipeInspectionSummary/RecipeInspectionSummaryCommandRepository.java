package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeInspectionSummaryCommandRepository extends JpaRepository<RecipeInspectionSummaryEntity, UUID> {
}
