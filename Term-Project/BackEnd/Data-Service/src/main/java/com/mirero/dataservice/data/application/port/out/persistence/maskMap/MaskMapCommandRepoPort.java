package com.mirero.dataservice.data.application.port.out.persistence.maskMap;

import com.mirero.dataservice.data.domain.MaskMap;

public interface MaskMapCommandRepoPort {

    MaskMap save(MaskMap maskMap);
}
