package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary.RecipeInspectionSummaryCommandRepoPort;
import com.mirero.dataservice.data.domain.RecipeInspectionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeInspectionSummaryCommandRepoAdaptor implements RecipeInspectionSummaryCommandRepoPort {

    private final RecipeInspectionSummaryCommandRepository recipeInspectionSummaryCommandRepository;

    @Override
    public RecipeInspectionSummary save(RecipeInspectionSummary recipeInspectionSummary) {
        return recipeInspectionSummaryCommandRepository.save(recipeInspectionSummary);
    }
}
