package com.erincinci;

import com.erincinci.exporters.IExporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.jvm.hotspot.utilities.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Application jUnit Tests
 * Created by erincinci on 04/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
@TestPropertySource({ "classpath:application-test.properties" })
public class ApplicationTest {
    @Autowired
    @Qualifier("csvExporter")
    private IExporter<String, Boolean> csvExporter;

    @Value("${output.csv.filename}")
    private String outputFilename;

    @Test
    public void shouldFindSuggestions() throws Exception {
        Assert.that(csvExporter.export("Ankara"), "CSV exporter was not successful!");

        File outputCsv = new File(System.getProperty("user.dir") + "/" + outputFilename);
        Assert.that(outputCsv.exists(), "Output CSV file not found!");
        Assert.that((countLines(outputCsv) == 3), "There should be 3 results for Ankara!");
    }

    @Test
    public void shouldNotFindSuggestions() throws Exception {
        Assert.that(!csvExporter.export("Hebele"), "Should have failed!");
        Assert.that(!csvExporter.export(""), "Should have failed!");
        Assert.that(!csvExporter.export(null), "Should have failed!");
    }

    /**
     * Auxiliary method for counting lines in a file
     * @param input
     * @return
     * @throws IOException
     */
    private int countLines(File input) throws IOException {
        try (InputStream is = new FileInputStream(input)) {
            int count = 0;
            for (int aChar = 0; aChar != -1; aChar = is.read())
                count += aChar == '\n' ? 1 : 0;
            return count;
        }
    }
}