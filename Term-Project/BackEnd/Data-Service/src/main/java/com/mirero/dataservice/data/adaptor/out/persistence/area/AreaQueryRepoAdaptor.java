package com.mirero.dataservice.data.adaptor.out.persistence.area;

import com.mirero.dataservice.data.application.port.out.persistence.area.AreaQueryRepoPort;
import com.mirero.dataservice.data.domain.entity.Area;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AreaQueryRepoAdaptor implements AreaQueryRepoPort {

    private final AreaQueryRepository areaQueryRepository;

    @Override
    public List<Area> findAllByEquipmentId(UUID equipmentId) {
        return areaQueryRepository.findAllByEquipmentId(equipmentId);
    }
}
