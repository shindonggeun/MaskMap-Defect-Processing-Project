package com.mirero.dataservice.data.adaptor.out.persistence.equipment;

import com.mirero.dataservice.data.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentQueryRepository extends JpaRepository<Equipment, Long> {

    boolean existsByFileName(String fileName);

    Optional<Equipment> findByFileName(String fileName);
}
