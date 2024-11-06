package com.mirero.dataservice.data.application.service.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.EquipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentQueryServiceImpl implements EquipmentQueryService {

    private final EquipmentQueryRepoPort queryRepoPort;
    private final EquipmentMapper mapper;

    @Override
    public EquipmentInfo getEquipmentByFileName(String fileName) {
        return queryRepoPort.findByFileName(fileName)
                .map(mapper::toEquipmentInfo)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }
}
