package com.mirero.dataservice.data.adaptor.out.persistence.classifyType;

import com.mirero.dataservice.data.application.port.out.persistence.classifyType.ClassifyTypeCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassifyTypeCommandRepoAdaptor implements ClassifyTypeCommandRepoPort {

    private final ClaasifyTypeCommandRepository claasifyTypeCommandRepository;

    @Override
    public List<ClassifyTypeEntity> saveAll(List<ClassifyTypeEntity> classifyTypeEntityList) {
        return claasifyTypeCommandRepository.saveAll(classifyTypeEntityList);
    }
}
