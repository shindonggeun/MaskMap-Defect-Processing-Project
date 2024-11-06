package com.mirero.dataservice.data.adaptor.in.web.maskMap.dto;

import lombok.Builder;

@Builder
public record MaskMapRequest(
        long equipmentId,
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
