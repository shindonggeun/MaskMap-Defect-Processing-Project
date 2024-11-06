package com.mirero.dataservice.data.adaptor.in.web.dto;

import lombok.Builder;

@Builder
public record AreaRequest(
        double dieSizeWidth,
        double dieSizeHeight,
        double diePitchX,
        double diePitchY,
        int dieCountX,
        int dieCountY
) {
}
