package com.mirero.dataservice.data.application.port.out.persistence.defect;

import com.mirero.dataservice.data.domain.entity.DefectEntity;

import java.util.List;

public interface DefectCommandRepoPort {

    List<DefectEntity> saveAll(List<DefectEntity> defectEntityList);
}
