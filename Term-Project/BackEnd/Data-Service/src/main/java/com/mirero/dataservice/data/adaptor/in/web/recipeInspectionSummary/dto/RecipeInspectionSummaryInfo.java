package com.mirero.dataservice.data.adaptor.in.web.recipeInspectionSummary.dto;

import java.time.LocalDateTime;

public record RecipeInspectionSummaryInfo(
        long id,
        String maskId,
        String lotId,
        String stepId,
        String deviceId,
        int inspType,
        long podId,
        double rotation,
        String equipId,
        String rcpVer,
        LocalDateTime scanTime,
        LocalDateTime endTime,
        double pixelSize,
        int totalStripeNumber,
        int startStripeNumber,
        String resultFolder,
        String inspNo
) {
}
