package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentCommandRepository extends JpaRepository<Equipment, Long> {
}
