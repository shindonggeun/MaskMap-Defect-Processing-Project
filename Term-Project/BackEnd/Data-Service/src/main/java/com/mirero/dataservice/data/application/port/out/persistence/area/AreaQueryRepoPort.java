package com.mirero.dataservice.data.application.port.out.persistence.area;

import com.mirero.dataservice.data.domain.entity.AreaEntity;

import java.util.List;
import java.util.UUID;

public interface AreaQueryRepoPort {

    List<AreaEntity> findAllByEquipmentId(UUID equipmentId);
}
