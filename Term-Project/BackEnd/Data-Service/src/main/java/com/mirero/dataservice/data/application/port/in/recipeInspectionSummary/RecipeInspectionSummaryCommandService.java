package com.mirero.dataservice.data.application.port.in.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.globalmodule.common.dto.LrfFileData;

import java.util.UUID;

public interface RecipeInspectionSummaryCommandService {

    RecipeInspectionSummaryInfo saveRecipeInspectionSummary(LrfFileData lrfFileData, UUID equipmentId);
}
