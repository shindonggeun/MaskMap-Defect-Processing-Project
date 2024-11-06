package com.mirero.dataservice.data.adaptor.in.web.dto;

import lombok.Builder;

@Builder
public record ClassifyTypeRequest(
        String name,
        String color,
        String number
) {
}
