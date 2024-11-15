package com.mirero.dataservice.data.application.service.mapper;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;
import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectRequest;
import com.mirero.dataservice.data.domain.entity.DefectEntity;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment.id", source = "equipmentId")
    DefectEntity toEntity(DefectRequest defectRequest);

    DefectInfo toDefectInfo(DefectEntity defectEntity);

    List<DefectInfo> toDefectInfoList(List<DefectEntity> defectEntityList);

    default List<DefectEntity> toEntityList(List<DefectRequest> defectRequestList) {
        return defectRequestList.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<DefectRequest> toDefectRequestList(LrfFileData lrfFileData, RffFileData rffFileData,
                                                    UUID equipmentId) {
        AtomicInteger indexCount = new AtomicInteger(0); // 스트림 내부 인덱스 관리를 위한 변수
        final int OFFSET_VALUE = 76200;

        return rffFileData.defectList().stream()
                .map(defect -> {
                    int index = indexCount.getAndIncrement();

                    int idx = defect.id();
                    int classifyNumber = defect.idxClassNo();
                    double sizeWidth = defect.size().width();
                    double sizeHeight = defect.size().height();
                    double relX = defect.relPosition().x();
                    double relY = defect.relPosition().y();
                    double area = defect.defectArea();

                    String uniqueId = lrfFileData.defectList().get(index).uniqueId();
                    double dieX = lrfFileData.defectList().get(index).diePosition().x();
                    double dieY = lrfFileData.defectList().get(index).diePosition().y();
                    double refX = lrfFileData.defectList().get(index).referencePosition().x() + OFFSET_VALUE;
                    double refY = lrfFileData.defectList().get(index).referencePosition().y() + OFFSET_VALUE;

                    return DefectRequest.builder()
                            .equipmentId(equipmentId)
                            .idx(idx)
                            .classifyNumber(classifyNumber)
                            .sizeWidth(sizeWidth)
                            .sizeHeight(sizeHeight)
                            .uniqueId(uniqueId)
                            .dieX(dieX)
                            .dieY(dieY)
                            .refX(refX)
                            .refY(refY)
                            .relX(relX)
                            .relY(relY)
                            .area(area)
                            .build();
                })
                .toList();
    }
}
