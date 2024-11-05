package com.mirero.ftpservice.ftp.adaptor.in.web;

import com.mirero.ftpservice.ftp.application.port.in.FileProcessingService;
import com.mirero.ftpservice.ftp.adaptor.in.web.dto.FileData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ftp")
public class FTPController {

    private final FileProcessingService fileProcessingService;

    @PostMapping("/file/process")
    public ResponseEntity<FileData> processFile(@RequestParam("file")MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        FileData result = fileProcessingService.processFile(tempFile.getAbsolutePath());

        tempFile.delete();
        return ResponseEntity.ok(result);
    }
}
