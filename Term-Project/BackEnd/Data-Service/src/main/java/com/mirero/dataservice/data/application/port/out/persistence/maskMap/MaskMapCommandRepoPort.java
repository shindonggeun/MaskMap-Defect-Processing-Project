package com.mirero.dataservice.data.application.port.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.entity.MaskMap;

public interface MaskMapCommandRepoPort {

    MaskMap save(MaskMap maskMap);
}
