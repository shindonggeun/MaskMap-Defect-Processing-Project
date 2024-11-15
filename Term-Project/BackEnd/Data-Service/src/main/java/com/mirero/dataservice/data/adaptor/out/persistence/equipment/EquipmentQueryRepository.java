package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.domain.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentQueryRepository extends JpaRepository<EquipmentEntity, UUID> {

    Optional<EquipmentEntity> findByFileName(String fileName);

    List<EquipmentEntity> findAllByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
