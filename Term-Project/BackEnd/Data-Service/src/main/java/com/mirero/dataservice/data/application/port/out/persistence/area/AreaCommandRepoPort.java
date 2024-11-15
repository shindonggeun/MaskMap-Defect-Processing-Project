package com.mirero.dataservice.data.application.port.out.persistence.area;

import com.mirero.dataservice.data.domain.entity.Area;

import java.util.List;

public interface AreaCommandRepoPort {

    List<Area> saveAll(List<Area> areaList);
}
