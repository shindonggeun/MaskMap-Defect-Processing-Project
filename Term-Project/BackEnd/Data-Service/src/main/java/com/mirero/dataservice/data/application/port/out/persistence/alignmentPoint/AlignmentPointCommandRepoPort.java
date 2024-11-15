package com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.entity.AlignmentPointEntity;

import java.util.List;

public interface AlignmentPointCommandRepoPort {

    List<AlignmentPointEntity> saveAll(List<AlignmentPointEntity> alignmentPointEntityList);
}
