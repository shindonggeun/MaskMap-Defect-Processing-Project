package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.application.port.out.persistence.area.AreaCommandRepoPort;
import com.mirero.dataservice.data.domain.entity.Area;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AreaCommandRepoAdaptor implements AreaCommandRepoPort {

    private final AreaCommandRepository areaCommandRepository;


    @Override
    public List<Area> saveAll(List<Area> areaList) {
        return areaCommandRepository.saveAll(areaList);
    }
}
