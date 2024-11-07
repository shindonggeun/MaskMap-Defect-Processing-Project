package com.mirero.dataservice.data.adaptor.in.web.classifyType.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClassifyTypeRequest(
        UUID equipmentId,
        String name,
        String color,
        int number
) {
}
