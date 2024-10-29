package com.mirero.ftpservice.ftp.adaptor.in;

import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData.ClassifyInformation;
import static com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData.AlignmentPoint;
import static com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData.Defect;
import static com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData.RelPosition;
import static com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData.Size;

public class RffFileParser implements Parser<RffFileData> {

    @Override
    public RffFileData parse(String filePath) {
        return null;
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
            String[] parts = reader.readLine().trim().split(" ");
            list.add(new ClassifyInformation(Integer.parseInt(parts[0]), parts[1].replace("\"", "")));
        }

        return list;
    }

    private List<AlignmentPoint> parseAlignmentPoints(BufferedReader reader, int count) throws IOException {
        List<AlignmentPoint> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String[] parts = reader.readLine().trim().split(" ");
            list.add(new AlignmentPoint(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
        }
        return list;
    }

    private List<Defect> parseDefectList(BufferedReader reader) throws IOException {
        List<Defect> list = new ArrayList<>();
        String line = "";

        while ((line = reader.readLine()) != null && !line.trim().equals("EndOfFile")) {
            String[] parts = line.split(" ");
            RelPosition relPosition = new RelPosition(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
            RffFileData.Size size = new Size(Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
            list.add(new Defect(Integer.parseInt(parts[0]), relPosition, size, Double.parseDouble(parts[7]), Integer.parseInt(parts[8])));
        }

        return list;
    }
}
