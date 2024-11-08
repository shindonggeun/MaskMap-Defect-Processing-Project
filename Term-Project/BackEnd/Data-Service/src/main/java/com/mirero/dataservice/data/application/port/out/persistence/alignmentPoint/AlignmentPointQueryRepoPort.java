package com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.AlignmentPoint;

import java.util.List;
import java.util.UUID;

public interface AlignmentPointQueryRepoPort {

    List<AlignmentPoint> findAllByEquipmentId(UUID equipmentId);
}
