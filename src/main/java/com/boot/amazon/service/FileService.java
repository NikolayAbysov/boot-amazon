package com.boot.amazon.service;

import java.io.IOException;

public interface FileService {
    void saveFileFromUrl(String loadFromUrl, String saveToPath) throws IOException;
}
