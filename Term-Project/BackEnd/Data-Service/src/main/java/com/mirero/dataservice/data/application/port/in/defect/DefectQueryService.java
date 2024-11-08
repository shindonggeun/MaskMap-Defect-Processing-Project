package com.mirero.dataservice.data.application.port.in.defect;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;

import java.util.List;
import java.util.UUID;

public interface DefectQueryService {

    List<DefectInfo> getDefectListByEquipmentId(UUID equipmentId);
}
