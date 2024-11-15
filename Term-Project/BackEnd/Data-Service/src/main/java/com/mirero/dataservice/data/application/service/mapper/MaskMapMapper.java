package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapRequest;
import com.mirero.dataservice.data.domain.document.MaskMapDocument;
import com.mirero.dataservice.data.domain.entity.MaskMapEntity;
import com.mirero.globalmodule.common.dto.RffFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MaskMapMapper {

    // Request 객체를 RDB의 엔티티로 변환
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    MaskMapEntity toEntity(MaskMapRequest maskMapRequest);

    // RDB의 엔티티를 Info DTO로 변환
    MaskMapInfo toMaskMapInfo(MaskMapEntity maskMapEntity);

    // NoSQL 기반 MongoDB 도큐먼트를 Info DTO로 변환 (조회용)
    MaskMapInfo toMaskMapInfo(MaskMapDocument maskMapDocument);

    default MaskMapRequest toMaskMapRequest(RffFileData rffFileData, UUID equipmentId) {
        return MaskMapRequest.builder()
                .equipmentId(equipmentId)
                .maskWidth(rffFileData.maskSize().width())
                .maskHeight(rffFileData.maskSize().height())
                .pixelWidth(rffFileData.pixelSize().width())
                .pixelHeight(rffFileData.pixelSize().height())
                .pitchX(rffFileData.die().pitchX())
                .pitchY(rffFileData.die().pitchY())
                .originX(rffFileData.die().originX())
                .originY(rffFileData.die().originY())
                .build();
    }
}
