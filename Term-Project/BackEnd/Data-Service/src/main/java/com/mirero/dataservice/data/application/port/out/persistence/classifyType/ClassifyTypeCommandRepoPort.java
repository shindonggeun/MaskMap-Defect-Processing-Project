package com.mirero.dataservice.data.application.port.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.entity.ClassifyTypeEntity;

import java.util.List;

public interface ClassifyTypeCommandRepoPort {

    List<ClassifyTypeEntity> saveAll(List<ClassifyTypeEntity> classifyTypeEntityList);
}
