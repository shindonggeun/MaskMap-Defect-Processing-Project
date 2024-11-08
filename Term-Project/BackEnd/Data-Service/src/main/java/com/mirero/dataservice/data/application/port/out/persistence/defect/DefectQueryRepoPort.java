package com.mirero.dataservice.data.application.port.out.persistence.defect;

import com.mirero.dataservice.data.domain.Defect;

import java.util.List;
import java.util.UUID;

public interface DefectQueryRepoPort {

    List<Defect> findAllByEquipmentId(UUID equipmentId);
}
