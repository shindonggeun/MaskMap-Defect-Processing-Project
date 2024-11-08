package com.mirero.dataservice.data.application.port.in.classifyType;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeQueryService {

    List<ClassifyTypeInfo> getClassifyTypeListByEquipmentId(UUID equipmentId);
}
