package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryRequest;
import com.mirero.dataservice.data.domain.RecipeInspectionSummary;
import com.mirero.globalmodule.common.dto.LrfFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeInspectionSummaryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    RecipeInspectionSummary toEntity(RecipeInspectionSummaryRequest recipeInspectionSummaryRequest);

    RecipeInspectionSummaryInfo toRecipeInspectionSummaryInfo(RecipeInspectionSummary recipeInspectionSummary);

    // LrfFileData를 RecipeInspectionSummaryRequest로 매핑하기 위한 커스텀 메서드
    default RecipeInspectionSummaryRequest toRecipeInspectionSummaryRequest(LrfFileData lrfFileData, Long equipmentId) {
        return RecipeInspectionSummaryRequest.builder()
                .equipmentId(equipmentId)
                .maskId(lrfFileData.recipeBasicInformation().maskId())
                .lotId(lrfFileData.recipeBasicInformation().lotId())
                .stepId(lrfFileData.recipeBasicInformation().stepId())
                .deviceId(lrfFileData.recipeBasicInformation().deviceId())
                .inspType(lrfFileData.recipeBasicInformation().inspType())
                .podId(lrfFileData.maskInformation().podId())
                .rotation(lrfFileData.maskInformation().rotation())
                .equipId(lrfFileData.resultBasicInformation().equipId())
                .rcpVer(lrfFileData.resultBasicInformation().rcpVer())
                .scanTime(lrfFileData.inspectionSummary().scanTime())
                .endTime(lrfFileData.inspectionSummary().endTime())
                .pixelSize(lrfFileData.inspectionSummary().pixelSize())
                .totalStripeNumber(lrfFileData.inspectionSummary().totalStripeNumber())
                .startStripeNumber(lrfFileData.inspectionSummary().startStripeNumber())
                .resultFolder(lrfFileData.inspectionSummary().resultFolder())
                .inspNo(lrfFileData.inspectionSummary().inspNo())
                .build();
    }
}
