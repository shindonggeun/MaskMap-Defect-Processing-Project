package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint.AlignmentPointCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.AlignmentPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlignmentPointPointCommandRepoAdaptor implements AlignmentPointCommandRepoPort {

    private final AlignmentPointCommandRepository alignmentPointCommandRepository;

    @Override
    public List<AlignmentPoint> saveAll(List<AlignmentPoint> alignmentPointList) {
        return alignmentPointCommandRepository.saveAll(alignmentPointList);
    }
}
