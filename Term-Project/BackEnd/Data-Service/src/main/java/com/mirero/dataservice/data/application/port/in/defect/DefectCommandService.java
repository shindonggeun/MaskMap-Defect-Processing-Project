package com.mirero.dataservice.data.application.port.in.defect;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;

import java.util.List;
import java.util.UUID;

public interface DefectCommandService {

    List<DefectInfo> saveDefectList(LrfFileData lrfFileData, RffFileData rffFileData, UUID equipmentId);
}
