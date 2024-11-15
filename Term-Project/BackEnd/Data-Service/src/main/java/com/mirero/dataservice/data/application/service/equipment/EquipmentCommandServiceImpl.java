package com.mirero.dataservice.data.application.service.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentCommandRepoPort;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.EquipmentMapper;
import com.mirero.dataservice.data.domain.entity.Equipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentCommandServiceImpl implements EquipmentCommandService {

    private final EquipmentQueryRepoPort queryRepoPort;
    private final EquipmentCommandRepoPort commandRepoPort;
    private final EquipmentMapper mapper;


    @Override
    public EquipmentInfo getOrCreateEquipment(String fileName) {
        return queryRepoPort.findByFileName(fileName)
                .map(mapper::toEquipmentInfo)
                .orElseGet(() -> {
                    Equipment entity = Equipment.builder()
                            .fileName(fileName)
                            .build();
                    Equipment savedEquipment = commandRepoPort.save(entity);
                    return mapper.toEquipmentInfo(savedEquipment);
                });
    }
}
