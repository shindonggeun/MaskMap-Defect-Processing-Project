package com.mirero.dataservice.data.application.service.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;
import com.mirero.dataservice.data.application.port.in.equipment.EquipmentQueryService;
import com.mirero.dataservice.data.application.port.out.persistence.equipment.EquipmentQueryRepoPort;
import com.mirero.dataservice.data.application.service.mapper.EquipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentQueryServiceImpl implements EquipmentQueryService {

    private final EquipmentQueryRepoPort queryRepoPort;
    private final EquipmentMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentInfo> getEquipmentListByCreatedDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(LocalTime.MAX);

        return queryRepoPort.findAllByCreatedAtBetween(startOfDay, endOfDay).stream()
                .map(mapper::toEquipmentInfo)
                .toList();
    }
}
