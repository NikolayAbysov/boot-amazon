package com.boot.amazon.service.impl;

import com.boot.amazon.service.ParseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParseServiceImplTest {
    private static final String PATH = "file.csv";
    private static File file;
    private ParseService parseService;

    @BeforeEach
    void init() throws IOException {
        parseService = new ParseServiceImpl();
        file = new File(PATH);
        String str = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
                + "HelpfulnessDenominator,Score,Time,Summary,Text\n"
                + "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400,"
                + "Good Quality Dog Food,I have bought several of the Vitality "
                + "canned dog food products and have found them all to be of good "
                + "quality. The product looks more like a stew than a processed "
                + "meat and it smells better. My Labrador is "
                + "finicky and she appreciates this product better than  most.";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }

    @AfterEach
    void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void parseFileOk() throws IOException {
        assertEquals(parseService.parse(file).size(), 1, "File should be parsed");
    }
}
