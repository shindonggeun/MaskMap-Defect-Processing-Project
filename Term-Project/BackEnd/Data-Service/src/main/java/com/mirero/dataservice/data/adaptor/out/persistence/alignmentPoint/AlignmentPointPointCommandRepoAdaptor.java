package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint.AlignmentPointCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.AlignmentPointEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlignmentPointPointCommandRepoAdaptor implements AlignmentPointCommandRepoPort {

    private final AlignmentPointCommandRepository alignmentPointCommandRepository;

    @Override
    public List<AlignmentPointEntity> saveAll(List<AlignmentPointEntity> alignmentPointEntityList) {
        return alignmentPointCommandRepository.saveAll(alignmentPointEntityList);
    }
}
