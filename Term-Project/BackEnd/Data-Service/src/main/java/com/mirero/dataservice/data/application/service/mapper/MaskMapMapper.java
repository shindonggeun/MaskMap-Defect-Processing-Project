package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapRequest;
import com.mirero.dataservice.data.domain.MaskMap;
import com.mirero.globalmodule.common.dto.RffFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MaskMapMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    MaskMap toEntity(MaskMapRequest maskMapRequest);

    MaskMapInfo toMaskMapInfo(MaskMap maskMap);

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
