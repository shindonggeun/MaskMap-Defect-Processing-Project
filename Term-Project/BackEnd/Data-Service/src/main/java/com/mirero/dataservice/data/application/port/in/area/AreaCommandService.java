package com.mirero.dataservice.data.application.port.in.area;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.globalmodule.common.dto.LrfFileData;

import java.util.List;

public interface AreaCommandService {

    List<AreaInfo> saveAreaList(LrfFileData lrfFileData, Long equipmentId);
}
