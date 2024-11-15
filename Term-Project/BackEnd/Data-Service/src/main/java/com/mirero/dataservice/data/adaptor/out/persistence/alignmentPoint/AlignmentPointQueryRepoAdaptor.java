package com.mirero.dataservice.data.adaptor.out.persistence.alignmentPoint;

import com.mirero.dataservice.data.application.port.out.persistence.alignmentPoint.AlignmentPointQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.AlignmentPointEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AlignmentPointQueryRepoAdaptor implements AlignmentPointQueryRepoPort {

    private final AlignmentPointQueryRepository alignmentPointQueryRepository;

    @Override
    public List<AlignmentPointEntity> findAllByEquipmentId(UUID equipmentId) {
        return alignmentPointQueryRepository.findAllByEquipmentId(equipmentId);
    }
}
