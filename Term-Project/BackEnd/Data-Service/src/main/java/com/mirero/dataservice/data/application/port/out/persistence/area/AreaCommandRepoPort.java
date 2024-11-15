package com.mirero.dataservice.data.application.port.out.persistence.area;

import com.mirero.dataservice.data.domain.entity.AreaEntity;

import java.util.List;

public interface AreaCommandRepoPort {

    List<AreaEntity> saveAll(List<AreaEntity> areaEntityList);
}
