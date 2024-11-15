package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeRequest;
import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ClassifyTypeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    ClassifyTypeEntity toEntity(ClassifyTypeRequest classifyTypeRequest);

    ClassifyTypeInfo toClassifyTypeInfo(ClassifyTypeEntity classifyTypeEntity);

    List<ClassifyTypeInfo> toClassifyTypeInfoList(List<ClassifyTypeEntity> classifyTypeEntityList);

    default List<ClassifyTypeEntity> toEntityList(List<ClassifyTypeRequest> classifyTypeRequestList) {
        return classifyTypeRequestList.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<ClassifyTypeRequest> toClassifyTypeRequestList(LrfFileData lrfFileData, RffFileData rffFileData,
                                                                UUID equipmentId) {
        return rffFileData.classifyInformationList().stream()
                .map(classifyInformation -> {
                    int number = classifyInformation.no();
                    String name = classifyInformation.name();

                    String color;

                    if (number == lrfFileData.classifyTypeInformationList().size()) {
                        color = lrfFileData.classifyTypeInformationList().getFirst().color();
                    }
                    else {
                        int lrfIndex = number - 1;
                        color = lrfFileData.classifyTypeInformationList().get(lrfIndex+1).color();
                    }

                    return ClassifyTypeRequest.builder()
                            .equipmentId(equipmentId)
                            .number(number)
                            .name(name)
                            .color(color)
                            .build();
                })
                .toList();
    }
}
