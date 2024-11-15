package com.mirero.dataservice.data.application.port.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.entity.MaskMapEntity;

public interface MaskMapCommandRepoPort {

    MaskMapEntity save(MaskMapEntity maskMapEntity);
}
