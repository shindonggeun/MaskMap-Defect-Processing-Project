package com.mirero.ftpservice.ftp.application.port.in;

import com.mirero.ftpservice.ftp.application.port.in.dto.FileData;

import java.io.IOException;

public interface FileProcessingService {
    FileData processFile(String filePath) throws IOException;
}
