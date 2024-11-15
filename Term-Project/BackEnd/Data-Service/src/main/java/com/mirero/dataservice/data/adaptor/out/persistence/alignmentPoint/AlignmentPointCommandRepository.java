package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.entity.AlignmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlignmentPointCommandRepository extends JpaRepository<AlignmentPoint, UUID> {
}
