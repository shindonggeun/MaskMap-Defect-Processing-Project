package com.mirero.dataservice.data.adaptor.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.ClassifyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeQueryRepository extends JpaRepository<ClassifyType, UUID> {

    List<ClassifyType> findAllByEquipmentId(UUID equipmentId);
}
