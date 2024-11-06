package com.mirero.dataservice.data.application.service.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentCommandRepoPort;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.EquipmentMapper;
import com.mirero.dataservice.data.domain.Equipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentCommandServiceImpl implements EquipmentCommandService {

    private final EquipmentQueryRepoPort equipmentQueryRepoPort;
    private final EquipmentCommandRepoPort equipmentCommandRepoPort;
    private final EquipmentMapper equipmentMapper;


    @Override
    public EquipmentInfo getOrCreateEquipment(String fileName) {
        return equipmentQueryRepoPort.findByFileName(fileName)
                .map(equipmentMapper::toEquipmentInfo)
                .orElseGet(() -> {
                    Equipment equipment = Equipment.builder()
                            .fileName(fileName)
                            .build();
                    Equipment savedEquipment = equipmentCommandRepoPort.save(equipment);
                    return equipmentMapper.toEquipmentInfo(savedEquipment);
                });
    }
}
