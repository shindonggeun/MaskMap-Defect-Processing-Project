package com.mirero.dataservice.data.application.service.defect;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;
import com.mirero.dataservice.data.application.port.in.defect.DefectQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.defect.DefectQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.DefectMapper;
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
public class DefectQueryServiceImpl implements DefectQueryService {

    private final DefectQueryRepoPort queryRepoPort;
    private final DefectMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<DefectInfo> getDefectListByEquipmentId(UUID equipmentId) {
        return mapper.toDefectInfoList(queryRepoPort.findAllByEquipmentId(equipmentId));
    }
}
