package com.boot.amazon.service;

import com.boot.amazon.dto.AbstractRecordDto;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ParseService<T extends AbstractRecordDto> {
    List<T> parse(File file) throws IOException;
}
