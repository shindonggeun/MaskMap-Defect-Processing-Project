package com.mirero.dataservice.data.application.port.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.entity.ClassifyType;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeQueryRepoPort {

    List<ClassifyType> findAllByEquipmentId(UUID equipmentId);
}
