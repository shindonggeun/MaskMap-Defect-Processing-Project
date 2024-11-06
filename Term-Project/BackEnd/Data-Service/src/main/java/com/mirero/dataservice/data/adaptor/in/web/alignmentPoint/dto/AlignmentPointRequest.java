package com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AlignmentPointRequest(
        UUID equipmentId,
        int idx,
        double x,
        double y
) {
}
