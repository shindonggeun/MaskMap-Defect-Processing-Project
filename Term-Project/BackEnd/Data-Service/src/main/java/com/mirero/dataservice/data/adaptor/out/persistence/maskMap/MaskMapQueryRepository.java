package com.mirero.dataservice.data.adaptor.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.MaskMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaskMapQueryRepository extends JpaRepository<MaskMap, UUID> {

    MaskMap findByEquipmentId(UUID equipmentId);
}
