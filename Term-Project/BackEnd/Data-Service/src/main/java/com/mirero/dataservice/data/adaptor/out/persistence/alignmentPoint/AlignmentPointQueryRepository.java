package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.entity.AlignmentPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlignmentPointQueryRepository extends JpaRepository<AlignmentPointEntity, UUID> {

    List<AlignmentPointEntity> findAllByEquipmentId(UUID equipmentId);
}
