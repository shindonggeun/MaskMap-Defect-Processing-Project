package com.mirero.dataservice.data.application.port.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.MaskMap;

import java.util.UUID;

public interface MaskMapQueryRepoPort {

    MaskMap findByEquipmentId(UUID equipmentId);
}
