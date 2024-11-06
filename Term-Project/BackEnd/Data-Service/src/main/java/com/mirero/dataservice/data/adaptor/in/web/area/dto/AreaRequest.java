package com.mirero.dataservice.data.adaptor.in.web.area.dto;

import lombok.Builder;

@Builder
public record AreaRequest(
        long equipmentId,
        double dieSizeWidth,
        double dieSizeHeight,
        double diePitchX,
        double diePitchY,
        int dieCountX,
        int dieCountY
) {
}
