package com.mirero.dataservice.data.adaptor.in.web.equipment.dto;

import java.util.UUID;

public record EquipmentInfo(
        UUID id,
        String fileName
) {
}
