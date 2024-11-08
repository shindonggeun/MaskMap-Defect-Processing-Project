package com.mirero.dataservice.data.application.service.alignmentPoint;

import com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto.AlignmentPointInfo;
import com.mirero.dataservice.data.application.port.in.alignmentPoint.AlignmentPointQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint.AlignmentPointQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.AlignmentPointMapper;
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
public class AlignmentPointQueryServiceImpl implements AlignmentPointQueryService {

    private final AlignmentPointQueryRepoPort queryRepoPort;
    private final AlignmentPointMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AlignmentPointInfo> getAlignmentPointListByEquipmentId(UUID equipmentId) {
        return mapper.toAlignmentPointInfoList(queryRepoPort.findAllByEquipmentId(equipmentId));
    }
}
