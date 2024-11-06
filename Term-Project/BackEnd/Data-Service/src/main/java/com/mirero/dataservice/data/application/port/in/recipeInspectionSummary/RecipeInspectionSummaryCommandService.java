package com.mirero.dataservice.data.application.port.in.recipeInspectionSummary;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryRequest;
import com.mirero.globalmodule.common.dto.LrfFileData;

public interface RecipeInspectionSummaryCommandService {

    RecipeInspectionSummaryInfo saveRecipeInspectionSummary(LrfFileData lrfFileData, Long equipmentId);
}
