package com.mirero.ftpservice.ftp.application.port.in.dto;

import java.time.LocalDateTime;
import java.util.List;

public record LrfFileData(
        RecipeBasicInformation recipeBasicInformation,
        MaskInformation maskInformation,
        ResultBasicInformation resultBasicInformation,
        InspectionSummary inspectionSummary,
        List<Defect> defectList,
        int defectCount // defects.size()
) implements FileData {
    public record RecipeBasicInformation(
            String maskId,// Modify.Name -> ex. //192.168.110.1/ACTIS/Recipe/Samsung/PA230803033-SSV000119-LV01-1B-90-DB.rcp; 에서 SSV000119을 가리킴
            String lotId,// Modify.Name -> ex. //192.168.110.1/ACTIS/Recipe/Samsung/PA230803033-SSV000119-LV01-1B-90-DB.rcp; 에서 PA230803033을 가리킴
            String stepId,// Modify.Name -> ex. //192.168.110.1/ACTIS/Recipe/Samsung/PA230803033-SSV000119-LV01-1B-90-DB.rcp; 에서 1B를 가리킴
            String deviceId,// Modify.Name -> ex. //192.168.110.1/ACTIS/Recipe/Samsung/PA230803033-SSV000119-LV01-1B-90-DB.rcp; 에서 LV01을 가리킴
            int inspType// Modify.Name -> ex. //192.168.110.1/ACTIS/Recipe/Samsung/PA230803033-SSV000119-LV01-1B-90-DB.rcp; 에서 90을 가리킴
    ) {}

    public record MaskInformation(
            long podId, // PodID -> ex. 11903135;에서 11903135을 가리킴
            double rotation // DesignTypeName -> ex. Samsung_0deg.drcp;에서 0을 가리킴
    ) {}

    public record ResultBasicInformation(
            String equipId, // Create.SerialNo -> ex. AC001B015;에서 AC001B015을 가리킴
            String rcpVer // Modify.AppVersion -> ex. 3.14.205.0;에서 3.14.205.0을 가리킴
    ) {}

    public record InspectionSummary(
            LocalDateTime scanTime, // InspectionStartTime -> ex. "2023/08/19 04:39:51";에서 2023/08/19 04:39:51을 가리킴
            LocalDateTime endTime, // InspectionFinishTime -> ex. "2023/08/19 09:14:19";에서 2023/08/19 09:14:19을 가리킴
            double pixelSize, // PixelSize -> ex. 0.0210004494에서 0.0210004494을 가리킴
            int totalStripeNumber, // TotalStripeNumber -> ex. 813;에서 813을 가리킴
            int startStripeNumber, // StartStripeNumber -> ex. 1;에서 1을 가리킴
            String resultFolder, // ResultFolder -> ex. PA230803033-SSV000119-1BDB-230819-043951;에서 PA230803033-SSV000119-1BDB-230819-043951을 가리킴
            String inspNo // Rff.CIMFileName -> ex. PA230803033-20230819-091419;에서 PA230803033-20230819-091419을 가리킴
    ) {}

    public record Defect(
            int no, // No
            String uniqueId, // UniqueID
            Size size,
            DiePosition diePosition,
            int classType,
            ReferencePosition referencePosition
    ) {}

    public record DiePosition(
            double x, // DieX
            double y // DieY
    ) {}

    public record Size(
            double width, // W
            double height // H
    ) {}

    public record ReferencePosition(
            double x,
            double y
    ) {}
}