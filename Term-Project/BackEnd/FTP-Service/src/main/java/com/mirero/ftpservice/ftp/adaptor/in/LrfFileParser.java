package com.mirero.ftpservice.ftp.adaptor.in;

import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.adaptor.in.web.dto.LrfFileData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mirero.ftpservice.ftp.adaptor.in.web.dto.LrfFileData.*;

public class LrfFileParser implements Parser<LrfFileData> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    public LrfFileData parse(String filePath, String fileName, String fileExtension) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            RecipeBasicInformation recipeBasicInformation = null;
            ResultBasicInformation resultBasicInformation = null;
            MaskInformation maskInformation = null;
            InspectionSummary inspectionSummary = null;
            List<AreaInformation> areaInformationList = new ArrayList<>();
            List<ClassifyTypeInformation> classifyTypeInformationList = new ArrayList<>();
            List<Defect> defectList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim().replace(";", "");

                if (line.startsWith("[RecipeBasicInformation]")) {
                    recipeBasicInformation = parseRecipeBasicInformation(reader);
                } else if (line.startsWith("[MaskInformation]")) {
                    maskInformation = parseMaskInformation(reader);
                } else if (line.startsWith("[AreaInformation]")) {
                    areaInformationList = parseAreaInformationList(reader);
                } else if (line.startsWith("[ResultBasicInformation]")) {
                    resultBasicInformation = parseResultBasicInformation(reader);
                } else if (line.startsWith("[InspectionSummary]")) {
                    inspectionSummary = parseInspectionSummary(reader);
                } else if (line.startsWith("[ClassifyTypeInformation]")) {
                    classifyTypeInformationList = parseClassifyTypeInformation(reader);
                } else if (line.startsWith("[DefectList]")) {
                    defectList = parseDefectList(reader);
                }
            }

            int defectCount = defectList.size();
            return new LrfFileData(fileName, fileExtension, recipeBasicInformation, maskInformation, areaInformationList,
                    resultBasicInformation, inspectionSummary, classifyTypeInformationList, defectList, defectCount);
        }
    }

    private RecipeBasicInformation parseRecipeBasicInformation(BufferedReader reader) throws IOException {
        String maskId = null;
        String lotId = null;
        String stepId = null;
        String deviceId = null;
        int inspType = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "");

            if (line.isEmpty()) {
                break;
            }

            if (line.startsWith("Modify.Name")) {
                String[] nameParts = line.split(" ")[1].split("/");
                String filename = nameParts[nameParts.length - 1]; // 파일명 추출
                String[] segments = filename.split("-");

                // 예시 파일명 형식: PA230803033-SSV000119-LV01-1B-90-DB.rcp
                lotId = segments[0];
                maskId = segments[1];
                deviceId = segments[2];
                stepId = segments[3];
                inspType = Integer.parseInt(segments[4]);
            }
        }

        return new RecipeBasicInformation(maskId, lotId, stepId, deviceId, inspType);
    }

    private MaskInformation parseMaskInformation(BufferedReader reader) throws IOException {
        long podId = 0;
        double rotation = 0.0;

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "");

            if (line.startsWith("[")) {
                break;
            }

            if (line.startsWith("PodID")) {
                podId = Long.parseLong(line.split(" ")[1]);
            } else if (line.startsWith("DesignTypeName")) {
                String designType = line.split(" ")[1];
                rotation = extractRotationFromDesignType(designType);
            }
        }

        return new MaskInformation(podId, rotation);
    }

    private double extractRotationFromDesignType(String designType) {
        String rotationPart = designType.split("_")[1].replace("deg.drcp", "");
        return Double.parseDouble(rotationPart);
    }

    private List<AreaInformation> parseAreaInformationList(BufferedReader reader) throws IOException {
        List<AreaInformation> list = new ArrayList<>();
        String line;

        DieSize dieSize = null;
        DiePitch diePitch = null;
        int dieCountX = 0;
        int dieCountY = 0;
        String currentAreaId = "";

        // 여러 구역을 읽기 위한 반복문
        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "");

            if (line.startsWith("[")) {
                break;
            }

            // 새로운 구역의 시작
            if (line.startsWith("Area")) {
                String[] areaAndKey = line.split("\\.");
                String areaId = areaAndKey[0];
                String areaKey = areaAndKey[1];

                // 새로운 Area 구역이 나타났다면 기존 구역 정보 리스트에 추가
                if (!currentAreaId.equals(areaId) && dieSize != null && diePitch != null) {
                    list.add(new AreaInformation(dieSize, diePitch, dieCountX, dieCountY));
                    // 새로운 구역을 위해 초기화
                    dieSize = null;
                    diePitch = null;
                    dieCountX = 0;
                    dieCountY = 0;
                }
                currentAreaId = areaId; // 현재 구역을 업데이트

                if (areaKey.startsWith("Width")) {
                    double width = Double.parseDouble(line.split(" ")[1]);
                    dieSize = new DieSize(width, dieSize == null ? 0.0 : dieSize.height());
                } else if (areaKey.startsWith("Height")) {
                    double height = Double.parseDouble(line.split(" ")[1]);
                    dieSize = new DieSize(dieSize == null ? 0.0 : dieSize.width(), height);
                } else if (areaKey.startsWith("HorzPitch")) {
                    double horzPitch = Double.parseDouble(line.split(" ")[1]);
                    diePitch = new DiePitch(horzPitch, diePitch == null ? 0.0 : diePitch.y());
                } else if (areaKey.startsWith("VertPitch")) {
                    double vertPitch = Double.parseDouble(line.split(" ")[1]);
                    diePitch = new DiePitch(diePitch == null ? 0.0 : diePitch.x(), vertPitch);
                } else if (areaKey.startsWith("HorzNumber")) {
                    dieCountX = Integer.parseInt(line.split(" ")[1]);
                } else if (areaKey.startsWith("VertNumber")) {
                    dieCountY = Integer.parseInt(line.split(" ")[1]);
                }
            }
        }

        // 마지막 구역 데이터 추가
        if (dieSize != null && diePitch != null) {
            list.add(new AreaInformation(dieSize, diePitch, dieCountX, dieCountY));
        }

        return list;
    }

    private ResultBasicInformation parseResultBasicInformation(BufferedReader reader) throws IOException {
        String equipId = null;
        String rcpVer = null;

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "");

            if (line.startsWith("[")) {
                break;
            }

            if (line.startsWith("Create.SerialNo")) {
                equipId = line.split(" ")[1];
            }
            else if (line.startsWith("Modify.AppVersion")) {
                rcpVer = line.split(" ")[1];
            }
        }

        return new ResultBasicInformation(equipId, rcpVer);
    }

    private InspectionSummary parseInspectionSummary(BufferedReader reader) throws IOException {
        LocalDateTime scanTime = null;
        LocalDateTime endTime = null;
        double pixelSize = 0.0;
        int totalStripeNumber = 0;
        int startStripeNumber = 0;
        String resultFolder = null;
        String inspNo = null;

        String line;

        // 이후 섹션을 확인할 수 있도록 reader에 mark 설정
        reader.mark(1000); // 1000은 충분히 큰 수를 설정하여 줄을 모두 읽을 수 있도록 함


        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "");

            if (line.startsWith("[")) {
                reader.reset(); // 이전 `mark` 위치로 돌아감
                break;
            }

            if (line.startsWith("InspectionStartTime")) {
                scanTime = parseDateTime(line.split(" ")[1] + " " + line.split(" ")[2]);
            } else if (line.startsWith("InspectionFinishTime")) {
                endTime = parseDateTime(line.split(" ")[1] + " " + line.split(" ")[2]);
            } else if (line.startsWith("PixelSize")) {
                pixelSize = Double.parseDouble(line.split(" ")[1]);
            } else if (line.startsWith("TotalStripeNumber")) {
                totalStripeNumber = Integer.parseInt(line.split(" ")[1]);
            } else if (line.startsWith("StartStripeNumber")) {
                startStripeNumber = Integer.parseInt(line.split(" ")[1]);
            } else if (line.startsWith("ResultFolder")) {
                resultFolder = line.split(" ")[1];
            } else if (line.startsWith("Rff.CIMFileName")) {
                inspNo = line.split(" ")[1];
            }

            // 줄을 읽기 전에 mark를 재설정하여 다음 루프에서 줄을 다시 읽을 수 있도록 함
            reader.mark(1000);
        }

        return new InspectionSummary(scanTime, endTime, pixelSize, totalStripeNumber, startStripeNumber, resultFolder, inspNo);
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        dateTimeString = dateTimeString.replace("\"", ""); // 따옴표 제거
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
    }

    private List<ClassifyTypeInformation> parseClassifyTypeInformation(BufferedReader reader) throws IOException {
        List<ClassifyTypeInformation> list = new ArrayList<>();
        String line;

        // "DefectType"으로 시작하는 부분까지 건너뜀
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("DefectType")) {
                break;
            }
        }

        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "").replaceAll("\\s+", " ");

            if (line.startsWith("[")) {
                break;
            }

            String[] parts = line.split(" ");
            list.add(new ClassifyTypeInformation(Integer.parseInt(parts[0]), parts[1], parts[2]));
        }

        return list;
    }

    private List<Defect> parseDefectList(BufferedReader reader) throws IOException {
        List<Defect> list = new ArrayList<>();
        String line;

        // "DefectDataList"로 시작하는 부분까지 건너뜀
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("DefectDataList")) {
                break;
            }
        }

        while ((line = reader.readLine()) != null) {
            line = line.trim().replace(";", "").replaceAll("\\s+", " ");

            String[] parts = line.split(" ");
            Size size = new Size(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
            DiePosition diePosition = new DiePosition(Double.parseDouble(parts[14]), Double.parseDouble(parts[15]));
            ReferencePosition referencePosition = new ReferencePosition(Double.parseDouble(parts[23]), Double.parseDouble(parts[24]));
            list.add(new Defect(Integer.parseInt(parts[0]), parts[1], size, diePosition, Integer.parseInt(parts[8]), referencePosition));
        }

        return list;
    }
}
