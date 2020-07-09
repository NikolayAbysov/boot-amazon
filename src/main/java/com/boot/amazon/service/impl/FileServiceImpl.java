package com.boot.amazon.service.impl;

import com.boot.amazon.service.FileService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileServiceImpl implements FileService {
    @Override
    public void saveFileFromUrl(String loadFromUrl, String saveToPath) throws IOException {
        URL url = new URL(loadFromUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(saveToPath);
        fileOutputStream.getChannel()
                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }
}
