package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.application.port.out.persistence.area.AreaCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.AreaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AreaCommandRepoAdaptor implements AreaCommandRepoPort {

    private final AreaCommandRepository areaCommandRepository;


    @Override
    public List<AreaEntity> saveAll(List<AreaEntity> areaEntityList) {
        return areaCommandRepository.saveAll(areaEntityList);
    }
}
