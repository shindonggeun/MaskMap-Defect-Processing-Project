package com.mirero.dataservice.data.application.service.maskMap;

import com.mirero.dataservice.data.adaptor.in.web.maskMap.dto.MaskMapInfo;
import com.mirero.dataservice.data.application.port.in.maskMap.MaskMapQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.maskMap.MaskMapQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.MaskMapMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MaskMapQueryServiceImpl implements MaskMapQueryService {

    private final MaskMapQueryRepoPort queryRepoPort;
    private final MaskMapMapper mapper;

    @Override
    public MaskMapInfo getMaskMapByEquipmentId(UUID equipmentId) {
        return mapper.toMaskMapInfo(queryRepoPort.findByEquipmentId(equipmentId));
    }
}
