package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.AlignmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlignmentPointQueryRepository extends JpaRepository<AlignmentPoint, UUID> {

    List<AlignmentPoint> findAllByEquipmentId(UUID equipmentId);
}
