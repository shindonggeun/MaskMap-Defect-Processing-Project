package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentCommandRepoAdaptor implements EquipmentCommandRepoPort {

    private final EquipmentCommandRepository equipmentCommandRepository;

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentCommandRepository.save(equipment);
    }
}
