package com.mirero.dataservice.data.application.service.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryRequest;
import com.mirero.dataservice.data.application.port.in.recipeInspectionSummary.RecipeInspectionSummaryCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.recipeInspectionSummary.RecipeInspectionSummaryCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.RecipeInspectionSummaryMapper;
import com.mirero.dataservice.data.domain.RecipeInspectionSummary;
import com.mirero.globalmodule.common.dto.LrfFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeInspectionSummaryCommandServiceImpl implements RecipeInspectionSummaryCommandService {

    private final RecipeInspectionSummaryCommandRepoPort commandRepoPort;
    private final RecipeInspectionSummaryMapper mapper;

    @Override
    public RecipeInspectionSummaryInfo saveRecipeInspectionSummary(LrfFileData lrfFileData, Long equipmentId) {
        RecipeInspectionSummaryRequest request = mapper.toRecipeInspectionSummaryRequest(lrfFileData, equipmentId);
        RecipeInspectionSummary entity = mapper.toEntity(request);
        RecipeInspectionSummary savedEntity = commandRepoPort.save(entity);

        return mapper.toRecipeInspectionSummaryInfo(savedEntity);
    }
}