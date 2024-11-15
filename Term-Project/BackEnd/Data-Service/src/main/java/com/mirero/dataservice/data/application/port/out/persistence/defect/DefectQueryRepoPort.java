package com.mirero.dataservice.data.application.port.out.persistence.defect;

import com.mirero.dataservice.data.domain.entity.DefectEntity;

import java.util.List;
import java.util.UUID;

public interface DefectQueryRepoPort {

    List<DefectEntity> findAllByEquipmentId(UUID equipmentId);
}
