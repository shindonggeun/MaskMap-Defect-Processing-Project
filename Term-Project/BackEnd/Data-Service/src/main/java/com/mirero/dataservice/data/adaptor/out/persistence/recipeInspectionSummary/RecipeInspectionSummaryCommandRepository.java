package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.domain.RecipeInspectionSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeInspectionSummaryCommandRepository extends JpaRepository<RecipeInspectionSummary, Long> {
}
