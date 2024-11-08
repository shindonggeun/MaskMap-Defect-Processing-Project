package com.mirero.dataservice.data.application.port.in.alignmentPoint;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;

import java.util.List;
import java.util.UUID;

public interface AlignmentPointQueryService {

    List<AlignmentPointInfo> getAlignmentPointListByEquipmentId(UUID equipmentId);
}
