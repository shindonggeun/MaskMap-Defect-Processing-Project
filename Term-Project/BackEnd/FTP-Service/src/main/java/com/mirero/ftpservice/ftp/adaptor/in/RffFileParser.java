package com.mirero.ftpservice.ftp.adaptor.in;

import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.adaptor.in.web.dto.RffFileData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mirero.ftpservice.ftp.adaptor.in.web.dto.RffFileData.*;

public class RffFileParser implements Parser<RffFileData> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public RffFileData parse(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            LocalDateTime scanTime = null;
            MaskSize maskSize = null;
            PixelSize pixelSize = null;
            Die die = null;
            double adjustUnit = 1.0;
            List<ClassifyInformation> classifyInformationList = new ArrayList<>();
            List<AlignmentPoint> alignmentPoints = new ArrayList<>();
            List<Defect> defectList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim().replace(";", ""); // 모든 ';' 제거
                if (line.startsWith("ResultTimestamp")) {
                    String[] parts = line.split(" ");
                    LocalDate date = LocalDate.parse(parts[1], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(parts[2], TIME_FORMATTER);
                    scanTime = LocalDateTime.of(date, time);
                } else if (line.startsWith("SampleSize")) {
                    String[] parts = line.split(" ");
                    maskSize = new MaskSize(Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                } else if (line.startsWith("PixelSize")) {
                    String[] parts = line.split(" ");
                    pixelSize = new PixelSize(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                } else if (line.startsWith("LengthUnit")) {
                    adjustUnit = getAdjustUnit(line.split(" ")[1]);
                } else if (line.startsWith("ClassLookup")) {
                    classifyInformationList = parseClassLookup(reader, Integer.parseInt(line.split(" ")[1]));
                } else if (line.startsWith("AlignmentPoints")) {
                    alignmentPoints = parseAlignmentPoints(reader, Integer.parseInt(line.split(" ")[1]));
                } else if (line.startsWith("DiePitch")) {
                    String[] parts = line.split(" ");
                    double pitchX = Double.parseDouble(parts[1]);
                    double pitchY = Double.parseDouble(parts[2]);

                    // 다음 줄에서 DieOrigin 읽기
                    line = reader.readLine().trim().replace(";", "");
                    if (line.startsWith("DieOrigin")) {
                        parts = line.split(" ");
                        double originX = Double.parseDouble(parts[1]);
                        double originY = Double.parseDouble(parts[2]);
                        die = new Die(pitchX, pitchY, originX, originY);
                    }
                } else if (line.startsWith("DefectList")) {
                    defectList = parseDefectList(reader);
                }
            }

            int defectCount = defectList.size();
            return new RffFileData(scanTime, maskSize, pixelSize, adjustUnit, classifyInformationList, alignmentPoints, die, defectList, defectCount);
        }
    }

    private double getAdjustUnit(String unit) {
        return switch (unit) {
            case "um" -> 1.0;
            case "mm" -> 1000.0;
            case "nm" -> 0.001;
            default -> 0.0;
        };
    }

    private List<ClassifyInformation> parseClassLookup(BufferedReader reader, int count) throws IOException {
        List<ClassifyInformation> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String line = reader.readLine().trim().replace(";", "").replaceAll("\\s+", " ");

            // 공백을 기준으로 첫 번째 부분은 번호, 나머지 부분은 이름으로 처리
            String[] parts = line.split(" ", 2);
            if (parts.length == 2) {
                int no = Integer.parseInt(parts[0]);  // 첫 번째 부분을 번호로
                String name = parts[1].replace("\"", "").trim();  // 두 번째 부분을 이름으로 처리 (따옴표 제거)

                // Defect Type으로 시작하면 괄호 안의 내용만 추출
                if (name.startsWith("Defect Type") && name.contains("(") && name.contains(")")) {
                    name = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
                }

                list.add(new ClassifyInformation(no, name));
            }
        }

        return list;
    }

    private List<AlignmentPoint> parseAlignmentPoints(BufferedReader reader, int count) throws IOException {
        List<AlignmentPoint> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String line = reader.readLine().trim().replace(";", "").replaceAll("\\s+", " ");
            String[] parts = line.split(" ");

            int idx = Integer.parseInt(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            list.add(new AlignmentPoint(idx, x, y));
        }
        return list;
    }

    private List<Defect> parseDefectList(BufferedReader reader) throws IOException {
        List<Defect> list = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            // DefectList가 종료되었는지 확인
            if (line.startsWith("SummarySpec") || line.startsWith("SummaryList") || line.startsWith("EndOfFile")) {
                break;
            }

            // 라인의 끝에 붙은 세미콜론(;) 제거 및 연속된 공백 처리
            line = line.replace(";", "").replaceAll("\\s+", " ");
            String[] parts = line.split(" ");
            RelPosition relPosition = new RelPosition(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
            Size size = new Size(Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
            list.add(new Defect(Integer.parseInt(parts[0]), relPosition, size, Double.parseDouble(parts[7]), Integer.parseInt(parts[8])));
        }

        return list;
    }
}
