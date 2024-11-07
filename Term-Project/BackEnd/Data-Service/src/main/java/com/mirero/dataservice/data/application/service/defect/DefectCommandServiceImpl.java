package com.mirero.dataservice.data.application.service.defect;

import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectInfo;
import com.mirero.dataservice.data.adaptor.in.web.defect.dto.DefectRequest;
import com.mirero.dataservice.data.application.port.in.defect.DefectCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.defect.DefectCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.DefectMapper;
import com.mirero.dataservice.data.domain.Defect;
import com.mirero.globalmodule.common.dto.LrfFileData;
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
public class DefectCommandServiceImpl implements DefectCommandService {

    private final DefectCommandRepoPort commandRepoPort;
    private final DefectMapper mapper;

    @Override
    public List<DefectInfo> saveDefectList(LrfFileData lrfFileData, RffFileData rffFileData, UUID equipmentId) {
        List<DefectRequest> requestList = mapper.toDefectRequestList(lrfFileData, rffFileData, equipmentId);
        List<Defect> entityList = mapper.toEntityList(requestList);
        List<Defect> savedEntityList = commandRepoPort.saveAll(entityList);

        return mapper.toDefectInfoList(savedEntityList);
    }
}
