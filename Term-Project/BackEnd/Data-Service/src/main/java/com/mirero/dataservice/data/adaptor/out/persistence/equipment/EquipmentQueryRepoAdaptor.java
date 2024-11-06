package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.domain.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EquipmentQueryRepoAdaptor implements EquipmentQueryRepoPort {

    private final EquipmentQueryRepository equipmentQueryRepository;

    @Override
    public boolean existsByFileName(String fileName) {
        return equipmentQueryRepository.existsByFileName(fileName);
    }

    @Override
    public Optional<Equipment> findById(UUID id) {
        return equipmentQueryRepository.findById(id);
    }

    @Override
    public Optional<Equipment> findByFileName(String fileName) {
        return equipmentQueryRepository.findByFileName(fileName);
    }
}
