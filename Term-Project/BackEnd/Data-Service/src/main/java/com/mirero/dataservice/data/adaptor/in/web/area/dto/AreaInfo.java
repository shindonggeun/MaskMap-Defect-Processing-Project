package com.mirero.dataservice.data.adaptor.in.web.area.dto;

public record AreaInfo(
        long id,
        double dieSizeWidth,
        double dieSizeHeight,
        double diePitchX,
        double diePitchY,
        int dieCountX,
        int dieCountY
) {
}
