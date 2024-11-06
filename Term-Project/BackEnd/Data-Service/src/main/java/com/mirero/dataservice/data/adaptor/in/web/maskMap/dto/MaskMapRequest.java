package com.mirero.dataservice.data.adaptor.in.web.maskMap.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MaskMapRequest(
        UUID equipmentId,
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
