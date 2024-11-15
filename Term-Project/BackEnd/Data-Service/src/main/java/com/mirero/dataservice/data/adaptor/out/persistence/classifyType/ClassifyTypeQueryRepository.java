package com.mirero.dataservice.data.adaptor.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassifyTypeQueryRepository extends JpaRepository<ClassifyTypeEntity, UUID> {

    List<ClassifyTypeEntity> findAllByEquipmentId(UUID equipmentId);
}
