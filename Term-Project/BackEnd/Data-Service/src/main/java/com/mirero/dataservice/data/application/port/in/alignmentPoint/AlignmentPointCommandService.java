package com.mirero.dataservice.data.application.port.in.alignmentPoint;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.globalmodule.common.dto.RffFileData;

import java.util.List;
import java.util.UUID;

public interface AlignmentPointCommandService {

    List<AlignmentPointInfo> saveAlignmentPointList(RffFileData rffFileData, UUID equipmentId);
}
