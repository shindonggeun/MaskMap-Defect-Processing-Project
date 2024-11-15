package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.entity.EquipmentEntity;

public interface EquipmentCommandRepoPort {

    EquipmentEntity save(EquipmentEntity equipmentEntity);
}
