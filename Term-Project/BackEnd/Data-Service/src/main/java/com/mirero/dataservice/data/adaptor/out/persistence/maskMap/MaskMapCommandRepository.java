package com.mirero.dataservice.data.adaptor.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.MaskMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaskMapCommandRepository extends JpaRepository<MaskMap, Long> {
}
