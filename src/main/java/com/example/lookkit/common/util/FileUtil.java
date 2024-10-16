package com.example.lookkit.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    private static final String UPLOAD_DIR = "C:/upload/";

    // 파일 저장 메서드
    public static String saveFile(MultipartFile file) throws IOException {
        File directory = new File("C:/upload");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File destination = new File(UPLOAD_DIR + fileName);
        file.transferTo(destination);
        return UPLOAD_DIR + fileName;
    }
}
