package com.mirero.globalmodule.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RffFileData(
        String fileName,
        String fileExtension,
        LocalDateTime scanTime, // ResultTimestamp 부분
        MaskSize maskSize, // sampleSize 부분
        PixelSize pixelSize, // PixelSize 부분
        double adjustUnit, // LengthUnit 부분 -> um = 1.0, mm = 1000.0, nm = 0.001
        List<ClassifyInformation> classifyInformationList, // ClassLookup 부분
        List<AlignmentPoint> alignmentPoints, // AlignmentPoints 부분
        Die die, // DiePitch, DieOrigin 부분
        List<Defect> defectList, // DefectList 부분
        int defectCount // defectList.size()
) implements FileData {
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
            int idx, // ex. 1 19600.000 19600.000에서 1을 가리킴
            double x, // ex. 1 19600.000 19600.000에서 19600.000을 가리킴
            double y // ex. 1 19600.000 19600.000에서 19600.000을 가리킴
    ) {}

    // Die 부분
    public record Die(
            double pitchX, // DiePitch -> ex. 30832.800 37799.200에서 30832.800을 가리킴
            double pitchY, // DiePitch -> ex. 30832.800 37799.200에서 37799.200을 가리킴
            double originX, // DieOrigin -> ex. 30070.800000 19621.200000에서 30070.800000을 가리킴
            double originY // DieOrigin -> ex. 30070.800000 19621.200000에서 19621.200000을 가리킴
    ) {}

    public record Defect(
            int id, // DEFECTID -> ex. 1에서 1을 가리킴
            RelPosition relPosition,
            Size size,
            double defectArea, // DEFECTAREA
            int idxClassNo // CLASSNUMBER
    ) {}

    public record RelPosition(
            double x, // XREL -> ex. 12058.423에서 12058.423을 가리킴
            double y // YREL -> ex. 58513.914에서 58513.914을 가리킴
    ) {}

    public record Size(
            double width, // XSIZE -> ex. 0.021에서 0.021을 가리킴
            double height // YSIZE -> ex. 0.021에서 0.021을 가리킴
    ) {}
}
