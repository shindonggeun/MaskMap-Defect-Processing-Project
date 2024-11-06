package com.mirero.dataservice.data.application.service.area;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaRequest;
import com.mirero.dataservice.data.application.port.in.area.AreaCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.area.AreaCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.AreaMapper;
import com.mirero.dataservice.data.domain.Area;
import com.mirero.globalmodule.common.dto.LrfFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AreaCommandServiceImpl implements AreaCommandService {

    private final AreaCommandRepoPort commandRepoPort;
    private final AreaMapper mapper;

    @Override
    public List<AreaInfo> saveAreaList(LrfFileData lrfFileData, Long equipmentId) {
        List<AreaRequest> requestList = mapper.toAreaRequestList(lrfFileData, equipmentId);
        List<Area> entityList = mapper.toEntityList(requestList);
        List<Area> savedEntityList = commandRepoPort.saveAll(entityList);

        return mapper.toAreaInfoList(savedEntityList);
    }
}
