package com.mirero.dataservice.data.application.port.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.entity.MaskMapEntity;

import java.util.UUID;

public interface MaskMapQueryRepoPort {

    MaskMapEntity findByEquipmentId(UUID equipmentId);
}
