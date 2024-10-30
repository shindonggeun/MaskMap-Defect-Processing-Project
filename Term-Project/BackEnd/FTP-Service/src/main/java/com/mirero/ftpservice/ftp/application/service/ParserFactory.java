package com.mirero.ftpservice.ftp.application.service;

import com.mirero.ftpservice.ftp.adaptor.in.LrfFileParser;
import com.mirero.ftpservice.ftp.adaptor.in.RffFileParser;
import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.ftpservice.ftp.application.port.in.dto.FileData;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {

    public Parser<? extends FileData> getParser(String fileExtension) {
        if ("rff".equalsIgnoreCase(fileExtension)) {
            return new RffFileParser();
        }
        else if ("lrf".equalsIgnoreCase(fileExtension)) {
            return new LrfFileParser();
        }
        return null;
    }
}
