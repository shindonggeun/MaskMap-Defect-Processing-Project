package com.mirero.dataservice.data.adaptor.in.web.classifyType.dto;

import java.util.UUID;

public record ClassifyTypeInfo(
        UUID id,
        int number,
        String name,
        String color
) {
}
