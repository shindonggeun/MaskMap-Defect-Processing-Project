package com.mirero.dataservice.data.application.port.in.maskMap;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.globalmodule.common.dto.RffFileData;

import java.util.UUID;

public interface MaskMapCommandService {

    MaskMapInfo saveMaskMap(RffFileData rffFileData, UUID equipmentId);
}
