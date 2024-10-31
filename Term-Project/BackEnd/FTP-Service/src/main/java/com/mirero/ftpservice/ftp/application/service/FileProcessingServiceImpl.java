package com.mirero.ftpservice.ftp.application.service;

import com.mirero.ftpservice.ftp.application.port.in.FileProcessingService;
import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.application.port.in.dto.FileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileProcessingServiceImpl implements FileProcessingService {

    private final ParserFactory parserFactory;

    @Override
    public FileData processFile(String filePath) throws IOException {
        String fileExtension = getFileExtension(filePath);
        log.info("Processing file: {}", filePath);
        Parser<? extends FileData> parser = parserFactory.getParser(fileExtension);
        return parser.parse(filePath);
    }

    private String getFileExtension(String filePath) {
        int lastIndexOf = filePath.lastIndexOf(".");
        return filePath.substring(lastIndexOf + 1);
    }
}
