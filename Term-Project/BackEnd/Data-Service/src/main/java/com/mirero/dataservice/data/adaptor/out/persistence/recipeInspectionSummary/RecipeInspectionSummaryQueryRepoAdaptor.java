package com.mirero.dataservice.data.adaptor.out.persistence.recipeInspectionSummary;

import com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary.RecipeInspectionSummaryQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecipeInspectionSummaryQueryRepoAdaptor implements RecipeInspectionSummaryQueryRepoPort {

    private final RecipeInspectionSummaryQueryRepository recipeInspectionSummaryQueryRepository;

    @Override
    public RecipeInspectionSummary findByEquipmentId(UUID equipmentId) {
        return recipeInspectionSummaryQueryRepository.findByEquipmentId(equipmentId);
    }
}
