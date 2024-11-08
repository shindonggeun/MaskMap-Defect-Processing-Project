package com.mirero.dataservice.data.application.service.area;

import com.mirero.dataservice.data.adaptor.in.web.area.dto.AreaInfo;
import com.mirero.dataservice.data.application.port.in.area.AreaQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.area.AreaQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.AreaMapper;
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
public class AreaQueryServiceImpl implements AreaQueryService {

    private final AreaQueryRepoPort queryRepoPort;
    private final AreaMapper mapper;

    @Override
    public List<AreaInfo> getAreaListByEquipmentId(UUID equipmentId) {
        return mapper.toAreaInfoList(queryRepoPort.findAllByEquipmentId(equipmentId));
    }
}
