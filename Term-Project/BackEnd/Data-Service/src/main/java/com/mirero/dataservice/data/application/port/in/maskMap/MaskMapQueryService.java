package com.mirero.dataservice.data.application.port.in.maskMap;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;

import java.util.UUID;

public interface MaskMapQueryService {

    MaskMapInfo getMaskMapByEquipmentId(UUID equipmentId);
}
