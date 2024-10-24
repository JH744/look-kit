package com.example.lookkit.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    // 실제 파일 시스템 경로
    private static final String UPLOAD_DIR = "C:/upload/inquiry/";

    public static String saveFile(MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File destination = new File(directory, fileName);
        file.transferTo(destination);
        System.out.println("파일명 확인: >>>>>>>>>>>" + destination.getAbsolutePath());
        return fileName;
    }
}
