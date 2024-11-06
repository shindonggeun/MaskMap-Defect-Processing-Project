package com.mirero.dataservice.data.adaptor.in.web.area.dto;

import java.util.UUID;

public record AreaInfo(
        UUID id,
        double dieSizeWidth,
        double dieSizeHeight,
        double diePitchX,
        double diePitchY,
        int dieCountX,
        int dieCountY
) {
}
