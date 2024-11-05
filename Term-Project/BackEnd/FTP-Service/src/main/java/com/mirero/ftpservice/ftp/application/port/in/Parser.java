package com.mirero.ftpservice.ftp.application.port.in;

import com.mirero.ftpservice.ftp.adaptor.in.web.dto.FileData;

import java.io.IOException;

public interface Parser<T extends FileData> {
    T parse(String filePath) throws IOException;
}
