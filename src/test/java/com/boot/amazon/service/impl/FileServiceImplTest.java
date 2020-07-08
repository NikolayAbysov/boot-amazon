package com.boot.amazon.service.impl;

import com.boot.amazon.service.FileService;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceImplTest {
    private static final String LOAD_FROM_URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private static final String SAVE_TO_PATH = "src/file.storage/file.txt";
    private static FileService fileService;
    private static File file;

    @BeforeEach
    void init() {
        fileService = new FileServiceImpl();
        file = new File(SAVE_TO_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void loadFileOk() throws IOException {
        fileService.loadFileByUrl(LOAD_FROM_URL, SAVE_TO_PATH);
        assertTrue(file.exists(), "File should exists");
    }

}