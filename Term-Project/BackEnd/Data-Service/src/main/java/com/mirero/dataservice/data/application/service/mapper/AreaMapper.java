package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaRequest;
import com.mirero.dataservice.data.domain.entity.AreaEntity;
import com.mirero.globalmodule.common.dto.LrfFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    AreaEntity toEntity(AreaRequest areaRequest);

    AreaInfo toAreaInfo(AreaEntity areaEntity);

    List<AreaInfo> toAreaInfoList(List<AreaEntity> areaEntityList);

    default List<AreaEntity> toEntityList(List<AreaRequest> areaRequestList) {
        return areaRequestList.stream()
                .map(this::toEntity) // 각 AreaRequest를 Area로 변환
                .collect(Collectors.toList());
    }

    default List<AreaRequest> toAreaRequestList(LrfFileData lrfFileData, UUID equipmentId) {
        return lrfFileData.areaInformationList().stream()
                .map(areaInfo -> AreaRequest.builder()
                        .equipmentId(equipmentId)
                        .dieSizeWidth(areaInfo.dieSize().width())
                        .dieSizeHeight(areaInfo.dieSize().height())
                        .diePitchX(areaInfo.diePitch().x())
                        .diePitchY(areaInfo.diePitch().y())
                        .dieCountX(areaInfo.dieCountX())
                        .dieCountY(areaInfo.dieCountY())
                        .build())
                .collect(Collectors.toList());
    }
}
