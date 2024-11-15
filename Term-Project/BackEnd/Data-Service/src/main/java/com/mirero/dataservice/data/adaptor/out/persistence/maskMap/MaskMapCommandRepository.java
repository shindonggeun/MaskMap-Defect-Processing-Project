package com.mirero.dataservice.data.adaptor.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.entity.MaskMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaskMapCommandRepository extends JpaRepository<MaskMapEntity, UUID> {
}
