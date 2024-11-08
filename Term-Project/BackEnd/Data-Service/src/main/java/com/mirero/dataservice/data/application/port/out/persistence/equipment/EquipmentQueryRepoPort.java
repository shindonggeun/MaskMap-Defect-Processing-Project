package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentQueryRepoPort {

    Optional<Equipment> findById(UUID id);

    Optional<Equipment> findByFileName(String fileName);

    List<Equipment> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
