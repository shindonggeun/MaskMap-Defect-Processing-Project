package com.mirero.ftpservice.ftp.application.service;

import com.mirero.ftpservice.ftp.adaptor.in.LrfFileParser;
import com.mirero.ftpservice.ftp.adaptor.in.RffFileParser;
import com.mirero.ftpservice.ftp.application.port.in.Parser;
import com.mirero.globalmodule.common.dto.FileData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParserFactory {

    public Parser<? extends FileData> getParser(String fileExtension) {
        log.info("파일 확장자명 확인: {}", fileExtension);

        if ("rff".equalsIgnoreCase(fileExtension)) {
            return new RffFileParser();
        }
        else if ("lrf".equalsIgnoreCase(fileExtension)) {
            return new LrfFileParser();
        }
        return null;
    }
}
