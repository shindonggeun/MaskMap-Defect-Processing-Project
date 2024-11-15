package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.domain.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentQueryRepository extends JpaRepository<Equipment, UUID> {

    Optional<Equipment> findByFileName(String fileName);

    List<Equipment> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
