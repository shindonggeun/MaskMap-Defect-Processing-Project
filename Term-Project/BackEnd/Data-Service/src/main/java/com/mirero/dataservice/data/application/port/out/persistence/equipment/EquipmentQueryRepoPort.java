package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;

import java.util.Optional;
import java.util.UUID;

public interface EquipmentQueryRepoPort {

    Optional<Equipment> findById(UUID id);

    Optional<Equipment> findByFileName(String fileName);

    boolean existsByFileName(String fileName);
}
