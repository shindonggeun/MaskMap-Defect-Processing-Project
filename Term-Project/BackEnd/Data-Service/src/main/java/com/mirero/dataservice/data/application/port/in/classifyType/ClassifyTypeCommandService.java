package com.mirero.dataservice.data.application.port.in.classifyType;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeCommandService {

    List<ClassifyTypeInfo> saveClassifyTypeList(LrfFileData lrfFileData, RffFileData rffFileData, UUID equipmentId);
}
