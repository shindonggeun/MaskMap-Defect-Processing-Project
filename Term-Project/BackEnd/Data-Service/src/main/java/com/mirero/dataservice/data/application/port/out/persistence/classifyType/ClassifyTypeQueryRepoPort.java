package com.mirero.dataservice.data.application.port.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeQueryRepoPort {

    List<ClassifyTypeEntity> findAllByEquipmentId(UUID equipmentId);
}
