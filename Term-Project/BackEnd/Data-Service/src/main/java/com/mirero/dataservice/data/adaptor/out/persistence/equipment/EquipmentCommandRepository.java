package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.domain.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentCommandRepository extends JpaRepository<EquipmentEntity, UUID> {
}
