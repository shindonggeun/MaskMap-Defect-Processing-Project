package com.mirero.dataservice.data.adaptor.out.persistence.defect;

import com.mirero.dataservice.data.application.port.out.persistence.defect.DefectCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.DefectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefectCommandRepoAdaptor implements DefectCommandRepoPort {

    private final DefectCommandRepository defectCommandRepository;

    @Override
    public List<DefectEntity> saveAll(List<DefectEntity> defectEntityList) {
        return defectCommandRepository.saveAll(defectEntityList);
    }
}
