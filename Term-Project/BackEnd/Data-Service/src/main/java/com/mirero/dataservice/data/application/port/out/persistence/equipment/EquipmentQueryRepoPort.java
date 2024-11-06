package com.mirero.dataservice.data.application.port.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;

import java.util.Optional;

public interface EquipmentQueryRepoPort {

    Optional<Equipment> findById(Long id);

    Optional<Equipment> findByFileName(String fileName);

    boolean existsByFileName(String fileName);
}
