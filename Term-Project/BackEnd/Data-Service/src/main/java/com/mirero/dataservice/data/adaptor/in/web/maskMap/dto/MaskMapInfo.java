package com.mirero.dataservice.data.adaptor.in.web.maskMap.dto;

public record MaskMapInfo(
        long id,
        double maskWidth,
        double maskHeight,
        double pixelWidth,
        double pixelHeight,
        double pitchX,
        double pitchY,
        double originX,
        double originY
) {
}
