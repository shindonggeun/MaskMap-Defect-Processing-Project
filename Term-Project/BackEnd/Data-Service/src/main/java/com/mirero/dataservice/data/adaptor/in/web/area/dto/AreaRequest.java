package com.mirero.dataservice.data.adaptor.in.web.area.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AreaRequest(
        UUID equipmentId,
        double dieSizeWidth,
        double dieSizeHeight,
        double diePitchX,
        double diePitchY,
        int dieCountX,
        int dieCountY
) {
}
