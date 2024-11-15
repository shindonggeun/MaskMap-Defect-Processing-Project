package com.mirero.dataservice.data.adaptor.out.persistence.classifyType;

import com.mirero.dataservice.data.application.port.out.persistence.classifyType.ClassifyTypeQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClassifyTypeQueryRepoAdaptor implements ClassifyTypeQueryRepoPort {

    private final ClassifyTypeQueryRepository classifyTypeQueryRepository;

    @Override
    public List<ClassifyTypeEntity> findAllByEquipmentId(UUID equipmentId) {
        return classifyTypeQueryRepository.findAllByEquipmentId(equipmentId);
    }
}
