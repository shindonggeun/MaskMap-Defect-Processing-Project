package com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.domain.entity.AlignmentPoint;

import java.util.List;

public interface AlignmentPointCommandRepoPort {

    List<AlignmentPoint> saveAll(List<AlignmentPoint> alignmentPointList);
}
