package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.domain.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AreaQueryRepository extends JpaRepository<AreaEntity, UUID> {

    List<AreaEntity> findAllByEquipmentId(UUID equipmentId);
}
