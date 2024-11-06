package com.mirero.dataservice.data.adaptor.in.web.alignmentPoint.dto;

import java.util.UUID;

public record AlignmentPointInfo(
        UUID id,
        int idx,
        double x,
        double y
) {
}
