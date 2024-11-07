package com.mirero.dataservice.data.adaptor.in.web.defect.dto;

import java.util.UUID;

public record DefectInfo(
        UUID id,
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
