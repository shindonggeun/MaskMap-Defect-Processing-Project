package com.mirero.dataservice.data.adaptor.out.persistence.maskMap;

import com.mirero.dataservice.data.application.port.out.persistence.maskMap.MaskMapQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.MaskMapEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MaskMapQueryRepoAdaptor implements MaskMapQueryRepoPort {

    private final MaskMapQueryRepository maskMapQueryRepository;

    @Override
    public MaskMapEntity findByEquipmentId(UUID equipmentId) {
        return maskMapQueryRepository.findByEquipmentId(equipmentId);
    }
}
