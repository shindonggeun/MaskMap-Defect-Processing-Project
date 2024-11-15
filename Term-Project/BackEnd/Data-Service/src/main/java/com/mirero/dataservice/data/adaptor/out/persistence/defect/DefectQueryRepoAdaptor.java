package com.mirero.dataservice.data.adaptor.out.persistence.defect;

import com.mirero.dataservice.data.application.port.out.persistence.defect.DefectQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.DefectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DefectQueryRepoAdaptor implements DefectQueryRepoPort {

    private final DefectQueryRepository defectQueryRepository;

    @Override
    public List<DefectEntity> findAllByEquipmentId(UUID equipmentId) {
        return defectQueryRepository.findAllByEquipmentId(equipmentId);
    }
}
