package com.mirero.ftpservice.ftp.application.port.in;

public interface Parser<T> {
    T parse(String filePath);
}
