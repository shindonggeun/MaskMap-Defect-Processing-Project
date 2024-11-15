package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryInfo;
import com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto.RecipeInspectionSummaryRequest;
import com.mirero.dataservice.data.domain.document.RecipeInspectionSummaryDocument;
import com.mirero.dataservice.data.domain.entity.RecipeInspectionSummaryEntity;
import com.mirero.globalmodule.common.dto.LrfFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RecipeInspectionSummaryMapper {

    // Request 객체를 RDB의 엔티티로 변환
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    RecipeInspectionSummaryEntity toEntity(RecipeInspectionSummaryRequest recipeInspectionSummaryRequest);

    // RDB의 엔티티를 Info DTO로 변환
    RecipeInspectionSummaryInfo toRecipeInspectionSummaryInfo(RecipeInspectionSummaryEntity recipeInspectionSummaryEntity);

    // RDB의 엔티티를 NoSQL 기반 MongoDB 도큐먼트로 변환
    @Mapping(target = "equipmentId", source = "equipment.id")
    RecipeInspectionSummaryDocument toDocument(RecipeInspectionSummaryEntity recipeInspectionSummaryEntity);

    // LrfFileData를 RecipeInspectionSummaryRequest로 매핑하기 위한 커스텀 메서드
    default RecipeInspectionSummaryRequest toRecipeInspectionSummaryRequest(LrfFileData lrfFileData, UUID equipmentId) {
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
