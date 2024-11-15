package com.mirero.dataservice.data.application.service.alignmentPoint;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointRequest;
import com.mirero.dataservice.data.application.port.in.alignmentPoint.AlignmentPointCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint.AlignmentPointCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.AlignmentPointMapper;
import com.mirero.dataservice.data.domain.entity.AlignmentPointEntity;
import com.mirero.globalmodule.common.dto.RffFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AlignmentPointCommandServiceImpl implements AlignmentPointCommandService {

    private final AlignmentPointCommandRepoPort commandRepoPort;
    private final AlignmentPointMapper mapper;

    @Override
    public List<AlignmentPointInfo> saveAlignmentPointList(RffFileData rffFileData, UUID equipmentId) {
        List<AlignmentPointRequest> requestList = mapper.toAlignmentPointRequestList(rffFileData, equipmentId);
        List<AlignmentPointEntity> entityList = mapper.toEntityList(requestList);
        List<AlignmentPointEntity> savedEntityList = commandRepoPort.saveAll(entityList);

        return mapper.toAlignmentPointInfoList(savedEntityList);
    }
}
