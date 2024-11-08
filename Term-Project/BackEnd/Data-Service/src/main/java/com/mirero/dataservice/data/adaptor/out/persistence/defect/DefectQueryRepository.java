package com.mirero.dataservice.data.adaptor.out.persistence.defect;

import com.mirero.dataservice.data.domain.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DefectQueryRepository extends JpaRepository<Defect, UUID> {

    List<Defect> findAllByEquipmentId(UUID equipmentId);
}
