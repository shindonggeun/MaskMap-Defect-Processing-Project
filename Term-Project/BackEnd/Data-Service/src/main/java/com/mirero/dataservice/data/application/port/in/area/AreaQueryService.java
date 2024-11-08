package com.mirero.dataservice.data.application.port.in.area;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;

import java.util.List;
import java.util.UUID;

public interface AreaQueryService {

    List<AreaInfo> getAreaListByEquipmentId(UUID equipmentId);
}
