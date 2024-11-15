package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointRequest;
import com.mirero.dataservice.data.domain.entity.AlignmentPoint;
import com.mirero.globalmodule.common.dto.RffFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AlignmentPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    AlignmentPoint toEntity(AlignmentPointRequest alignmentPointRequest);

    AlignmentPointInfo toAlignmentPointInfo(AlignmentPoint alignmentPoint);

    List<AlignmentPointInfo> toAlignmentPointInfoList(List<AlignmentPoint> alignmentPointList);

    default List<AlignmentPoint> toEntityList(List<AlignmentPointRequest> alignmentPointRequestList) {
        return alignmentPointRequestList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    default List<AlignmentPointRequest> toAlignmentPointRequestList(RffFileData rffFileData, UUID equipmentId) {
        return rffFileData.alignmentPointList().stream()
                .map(alignmentPoint -> AlignmentPointRequest.builder()
                        .equipmentId(equipmentId)
                        .idx(alignmentPoint.idx())
                        .x(alignmentPoint.x())
                        .y(alignmentPoint.y())
                        .build())
                .collect(Collectors.toList());
    }
}
