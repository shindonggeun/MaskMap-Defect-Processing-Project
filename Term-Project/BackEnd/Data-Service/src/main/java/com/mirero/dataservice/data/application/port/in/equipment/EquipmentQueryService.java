package com.mirero.dataservice.data.application.port.in.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;

public interface EquipmentQueryService {

    EquipmentInfo getEquipmentByFileName(String fileName);
}
