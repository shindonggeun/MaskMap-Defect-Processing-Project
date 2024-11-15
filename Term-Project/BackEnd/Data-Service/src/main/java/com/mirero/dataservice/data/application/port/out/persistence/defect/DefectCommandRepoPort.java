package com.mirero.dataservice.data.application.port.out.persistence.defect;

import com.mirero.dataservice.data.domain.entity.Defect;

import java.util.List;

public interface DefectCommandRepoPort {

    List<Defect> saveAll(List<Defect> defectList);
}
