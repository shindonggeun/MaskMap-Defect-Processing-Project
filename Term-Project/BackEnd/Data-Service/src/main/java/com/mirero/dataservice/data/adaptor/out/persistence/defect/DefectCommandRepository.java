package com.mirero.dataservice.data.adaptor.out.persistence.defect;

import com.mirero.dataservice.data.domain.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DefectCommandRepository extends JpaRepository<Defect, UUID> {
}
