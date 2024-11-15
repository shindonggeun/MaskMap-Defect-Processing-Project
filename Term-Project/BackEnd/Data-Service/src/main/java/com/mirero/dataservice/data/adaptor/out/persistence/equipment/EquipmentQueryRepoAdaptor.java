package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.EquipmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EquipmentQueryRepoAdaptor implements EquipmentQueryRepoPort {

    private final EquipmentQueryRepository equipmentQueryRepository;

    @Override
    public Optional<EquipmentEntity> findById(UUID id) {
        return equipmentQueryRepository.findById(id);
    }

    @Override
    public Optional<EquipmentEntity> findByFileName(String fileName) {
        return equipmentQueryRepository.findByFileName(fileName);
    }

    @Override
    public List<EquipmentEntity> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        return equipmentQueryRepository.findAllByCreatedAtBetween(startOfDay, endOfDay);
    }
}
