package com.mirero.dataservice.data.adaptor.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.ClassifyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaasifyTypeCommandRepository extends JpaRepository<ClassifyType, UUID> {
}
