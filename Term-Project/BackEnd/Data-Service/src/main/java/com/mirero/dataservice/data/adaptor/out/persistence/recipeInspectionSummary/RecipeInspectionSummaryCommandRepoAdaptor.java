package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary.RecipeInspectionSummaryCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummaryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeInspectionSummaryCommandRepoAdaptor implements RecipeInspectionSummaryCommandRepoPort {

    private final RecipeInspectionSummaryCommandRepository recipeInspectionSummaryCommandRepository;

    @Override
    public RecipeInspectionSummaryEntity save(
        RecipeInspectionSummaryEntity recipeInspectionSummaryEntity) {
        return recipeInspectionSummaryCommandRepository.save(recipeInspectionSummaryEntity);
    }
}
