package com.mirero.ftpservice.ftp.application.service;

import com.mirero.ftpservice.ftp.adaptor.in.LrfFileParser;
import com.mirero.ftpservice.ftp.adaptor.in.RffFileParser;
import com.mirero.ftpservice.ftp.application.port.in.FileProcessingService;
import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.application.port.in.dto.FileData;
import com.mirero.ftpservice.ftp.application.port.in.dto.LrfFileData;
import com.mirero.ftpservice.ftp.application.port.in.dto.RffFileData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileProcessingServiceImpl implements FileProcessingService {

    private final ParserFactory parserFactory;

    @Override
    public FileData processFile(String filePath) throws IOException {
        String fileExtension = getFileExtension(filePath);
        Parser<? extends FileData> parser = parserFactory.getParser(fileExtension);
        return parser.parse(filePath);
    }

    private String getFileExtension(String filePath) {
        int lastIndexOf = filePath.lastIndexOf(".");
        return filePath.substring(lastIndexOf + 1);
    }
}
