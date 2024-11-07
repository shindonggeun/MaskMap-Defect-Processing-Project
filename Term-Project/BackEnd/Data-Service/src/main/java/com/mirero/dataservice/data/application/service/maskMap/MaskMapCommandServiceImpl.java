package com.mirero.dataservice.data.application.service.maskMap;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapRequest;
import com.mirero.dataservice.data.application.port.in.maskMap.MaskMapCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.maskMap.MaskMapCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.MaskMapMapper;
import com.mirero.dataservice.data.domain.MaskMap;
import com.mirero.globalmodule.common.dto.RffFileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MaskMapCommandServiceImpl implements MaskMapCommandService {

    private final MaskMapCommandRepoPort commandRepoPort;
    private final MaskMapMapper mapper;

    @Override
    public MaskMapInfo saveMaskMap(RffFileData rffFileData, UUID equipmentId) {
        MaskMapRequest request = mapper.toMaskMapRequest(rffFileData, equipmentId);
        MaskMap entity = mapper.toEntity(request);
        MaskMap savedEntity = commandRepoPort.save(entity);

        return mapper.toMaskMapInfo(savedEntity);
    }
}
