package com.mirero.dataservice.data.application.port.in.equipment;

import com.mirero.dataservice.data.adaptor.in.web.equipment.dto.EquipmentInfo;

import java.time.LocalDate;
import java.util.List;

public interface EquipmentQueryService {

    List<EquipmentInfo> getEquipmentListByCreatedDateRange(LocalDate startDate, LocalDate endDate);
}
