package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.entity.EquipmentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentQueryRepoPort {

    Optional<EquipmentEntity> findById(UUID id);

    Optional<EquipmentEntity> findByFileName(String fileName);

    List<EquipmentEntity> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
