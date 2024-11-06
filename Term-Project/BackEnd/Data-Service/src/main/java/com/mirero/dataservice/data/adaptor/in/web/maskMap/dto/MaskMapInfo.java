package com.mirero.dataservice.data.adaptor.in.web.maskMap.dto;

import java.util.UUID;

public record MaskMapInfo(
        UUID id,
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
