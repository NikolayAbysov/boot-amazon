package com.boot.amazon.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import com.boot.amazon.dto.CSVRecordDto;
import com.boot.amazon.service.ParseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ParseServiceImpl implements ParseService {
    private String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};

    @Override
    public List<CSVRecordDto> parse(File file) throws IOException {
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        return mapToDto(records);
    }

    private List<CSVRecordDto> mapToDto(Iterable<CSVRecord> records) {
        List<CSVRecordDto> recordDtos = new ArrayList<>();
        CSVRecordDto recordDto;
        for (CSVRecord record : records) {
            recordDto = new CSVRecordDto();
            recordDto.setId(Integer.parseInt(record.get("Id")));
            recordDto.setProductId(record.get("ProductId"));
            recordDto.setUserId(record.get("UserId"));
            recordDto.setProfileName(record.get("ProfileName"));
            recordDto.setHelpfulnessNumerator(Integer.parseInt(record.get("HelpfulnessNumerator")));
            recordDto.setHelpfulnessDenominator(Integer.parseInt(record.get("HelpfulnessDenominator")));
            recordDto.setScore(Integer.parseInt(record.get("Score")));
            recordDto.setTime(Long.parseLong(record.get("Time")));
            recordDto.setSummary(record.get("Summary"));
            recordDto.setText(record.get("Text"));
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }
}
