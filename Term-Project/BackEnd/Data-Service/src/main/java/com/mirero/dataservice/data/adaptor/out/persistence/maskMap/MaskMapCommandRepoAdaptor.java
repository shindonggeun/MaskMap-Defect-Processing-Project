package com.mirero.dataservice.data.adaptor.out.persistence.maskMap;

import com.mirero.dataservice.data.application.port.out.persistence.maskMap.MaskMapCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.MaskMapEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaskMapCommandRepoAdaptor implements MaskMapCommandRepoPort {

    private final MaskMapCommandRepository maskMapCommandRepository;

    @Override
    public MaskMapEntity save(MaskMapEntity maskMapEntity) {
        return maskMapCommandRepository.save(maskMapEntity);
    }
}
