package com.mirero.dataservice.data.application.service.classifyType;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.dataservice.data.application.port.in.classifyType.ClassifyTypeQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.classifyType.ClassifyTypeQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.ClassifyTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClassifyTypeQueryServiceImpl implements ClassifyTypeQueryService {

    private final ClassifyTypeQueryRepoPort queryRepoPort;
    private final ClassifyTypeMapper mapper;

    @Override
    public List<ClassifyTypeInfo> getClassifyTypeListByEquipmentId(UUID equipmentId) {
        return mapper.toClassifyTypeInfoList(queryRepoPort.findAllByEquipmentId(equipmentId));
    }
}
