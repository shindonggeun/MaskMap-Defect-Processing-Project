package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;

public interface EquipmentCommandRepoPort {

    Equipment save(Equipment equipment);
}