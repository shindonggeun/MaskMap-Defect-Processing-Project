package com.mirero.dataservice.data.adaptor.out.persistence.defect;

import com.mirero.dataservice.data.application.port.out.persistence.defect.DefectCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.Defect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefectCommandRepoAdaptor implements DefectCommandRepoPort {

    private final DefectCommandRepository defectCommandRepository;

    @Override
    public List<Defect> saveAll(List<Defect> defectList) {
        return defectCommandRepository.saveAll(defectList);
    }
}
