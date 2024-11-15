package com.mirero.dataservice.data.application.port.out.persistence.classifyType;

import com.mirero.dataservice.data.domain.entity.ClassifyType;

import java.util.List;

public interface ClassifyTypeCommandRepoPort {

    List<ClassifyType> saveAll(List<ClassifyType> classifyTypeList);
}
