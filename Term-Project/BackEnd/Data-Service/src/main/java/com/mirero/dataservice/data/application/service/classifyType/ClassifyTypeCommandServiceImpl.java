package com.mirero.dataservice.data.application.service.classifyType;

import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeInfo;
import com.mirero.dataservice.data.adaptor.in.web.classifyType.dto.ClassifyTypeRequest;
import com.mirero.dataservice.data.application.port.in.classifyType.ClassifyTypeCommandService;
import com.mirero.dataservice.data.application.port.out.persistence.classifyType.ClassifyTypeCommandRepoPort;
import com.mirero.dataservice.data.application.service.mapper.ClassifyTypeMapper;
import com.mirero.dataservice.data.domain.entity.ClassifyType;
import com.mirero.globalmodule.common.dto.LrfFileData;
import com.mirero.globalmodule.common.dto.RffFileData;
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
public class ClassifyTypeCommandServiceImpl implements ClassifyTypeCommandService {

    private final ClassifyTypeCommandRepoPort commandRepoPort;
    private final ClassifyTypeMapper mapper;

    @Override
    public List<ClassifyTypeInfo> saveClassifyTypeList(LrfFileData lrfFileData, RffFileData rffFileData, UUID equipmentId) {
        List<ClassifyTypeRequest> requestList = mapper.toClassifyTypeRequestList(lrfFileData, rffFileData, equipmentId);
        List<ClassifyType> entityList = mapper.toEntityList(requestList);
        List<ClassifyType> savedEntityList = commandRepoPort.saveAll(entityList);

        return mapper.toClassifyTypeInfoList(savedEntityList);
    }
}
