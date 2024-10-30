package com.mirero.ftpservice.ftp.adaptor.in;

import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.application.port.in.dto.LrfFileData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mirero.ftpservice.ftp.application.port.in.dto.LrfFileData.*;

public class LrfFileParser implements Parser<LrfFileData> {

    @Override
    public LrfFileData parse(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            RecipeBasicInformation recipeBasicInformation = null;
            ResultBasicInformation resultBasicInformation = null;
            MaskInformation maskInformation = null;
            InspectionSummary inspectionSummary = null;
            List<Defect> defectList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim().replace(";", "");

                if (line.startsWith("[RecipeBasicInformation]")) {
                    recipeBasicInformation = parseRecipeBasicInformation(reader);
                }
                else if (line.startsWith("[ResultBasicInformation]")) {
                    resultBasicInformation = parseResultBasicInformation(reader);
                } else if (line.startsWith("[DefectList]")) {
                    defectList = parseDefectList(reader);
                }
            }

            int defectCount = defectList.size();
            return new LrfFileData(recipeBasicInformation, resultBasicInformation, maskInformation, inspectionSummary, defectList, defectCount);
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

            if (line.startsWith("[")) {
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
