package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.domain.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaCommandRepository extends JpaRepository<Area, UUID> {
}
