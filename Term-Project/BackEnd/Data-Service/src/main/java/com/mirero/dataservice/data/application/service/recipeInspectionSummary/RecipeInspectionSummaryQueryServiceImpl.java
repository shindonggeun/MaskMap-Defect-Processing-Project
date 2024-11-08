package com.mirero.dataservice.data.application.service.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.application.port.in.recipeInspectionSummary.RecipeInspectionSummaryQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary.RecipeInspectionSummaryQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.RecipeInspectionSummaryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecipeInspectionSummaryQueryServiceImpl implements RecipeInspectionSummaryQueryService {

    private final RecipeInspectionSummaryQueryRepoPort queryRepoPort;
    private final RecipeInspectionSummaryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public RecipeInspectionSummaryInfo getRecipeInspectionSummaryByEquipmentId(UUID equipmentId) {
        return mapper.toRecipeInspectionSummaryInfo(queryRepoPort.findByEquipmentId(equipmentId));
    }
}
