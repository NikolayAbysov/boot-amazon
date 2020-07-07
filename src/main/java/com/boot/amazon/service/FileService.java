package com.boot.amazon.service;

import java.io.IOException;

public interface FileService {
    void loadFileByUrl(String loadFromUrl, String saveToPath) throws IOException;
}
