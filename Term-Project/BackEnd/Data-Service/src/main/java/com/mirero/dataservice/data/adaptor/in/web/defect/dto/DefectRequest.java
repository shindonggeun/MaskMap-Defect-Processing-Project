package com.mirero.dataservice.data.adaptor.in.web.defect.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DefectRequest(
        UUID equipmentId,
        int idx,
        int classifyNumber,
        double sizeWidth,
        double sizeHeight,
        String uniqueId,
        double dieX,
        double dieY,
        double refX,
        double refY,
        double relX,
        double relY,
        double area
) {
}
