package com.boot.amazon.service.impl;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.service.ParseService;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class ParseServiceImpl implements ParseService {
    private static final String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};

    @Override
    public List<CsvRecordDto> parse(File file) throws IOException {
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        return mapToDto(records);
    }

    private List<CsvRecordDto> mapToDto(Iterable<CSVRecord> records) {
        List<CsvRecordDto> recordDtos = new ArrayList<>();
        CsvRecordDto recordDto;
        for (CSVRecord record : records) {
            recordDto = new CsvRecordDto();
            recordDto.setId(Long.parseLong(record.get("Id")));
            recordDto.setProductId(record.get("ProductId"));
            recordDto.setUserId(record.get("UserId"));
            recordDto.setProfileName(record.get("ProfileName"));
            recordDto.setHelpfulnessNumerator(Integer
                    .parseInt(record.get("HelpfulnessNumerator")));
            recordDto.setHelpfulnessDenominator(Integer
                    .parseInt(record.get("HelpfulnessDenominator")));
            recordDto.setScore(Integer.parseInt(record.get("Score")));
            recordDto.setLocalDate(LocalDate.ofInstant(Instant
                    .ofEpochSecond(Long.parseLong(record.get("Time"))),
                    TimeZone.getDefault().toZoneId()));
            recordDto.setSummary(record.get("Summary"));
            recordDto.setText(record.get("Text"));
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }
}
