package com.mirero.ftpservice.ftp.application.port.in.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RffFileData(
        LocalDateTime scanTime, // ResultTimestamp 부분
        MaskSize maskSize, // sampleSize 부분
        PixelSize pixelSize, // PixelSize 부분
        double adjustUnit, // LengthUnit 부분 -> um = 1.0, mm = 1000.0, nm = 0.001
        List<ClassifyInformation> classifyInformationList, // ClassLookup 부분
        List<AlignmentPoint> alignmentPoints, // AlignmentPoints 부분
        List<Defect> defectList, // DefectList 부분
        int defectCount // defectList.size()
) {
    public record MaskSize(
            double width, // ex. SampleSize 2 152400 152400에서 2 부분 (2.0)
            double height // ex. SampleSize 2 152400 152400에서 152400 부분 (152400.0)
    ) {}

    public record PixelSize(
            double width, // ex. PixelSize 0.021000 0.02100에서 0.021000을 가리킴
            double height // ex. PixelSize 0.021000 0.02100에서 0.021000을 가리킴
    ) {}

    // ClassLookup 부분
    public record ClassifyInformation(
            int no, // ex. 1 "1A"에서 1을 가리킴
            String name // ex. 1 "1A"에서 1A를 가리킴
    ) {}

    // AlignmentPoints 부분
    public record AlignmentPoint(
            int id, // ex. 1 19600.000 19600.000에서 1을 가리킴
            double x, // ex. 1 19600.000 19600.000에서 19600.000을 가리킴
            double y // ex. 1 19600.000 19600.000에서 19600.000을 가리킴
    ) {}

    public record Defect(
            int id, // DEFECTID
            RelPosition relPosition,
            Size size,
            double defectArea, // DEFECTAREA
            int idxClassNo // CLASSNUMBER
    ) {}

    public record RelPosition(
            double x, // XREL
            double y // YREL
    ) {}

    public record Size(
            double width, // XSIZE
            double height // YSIZE
    ) {}
}
