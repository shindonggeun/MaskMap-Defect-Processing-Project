package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaCommandRepository extends JpaRepository<Area, Long> {
}
