package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentCommandRepoAdaptor implements EquipmentCommandRepoPort {

    private final EquipmentCommandRepository equipmentCommandRepository;

    @Override
    public EquipmentEntity save(EquipmentEntity equipmentEntity) {
        return equipmentCommandRepository.save(equipmentEntity);
    }
}
