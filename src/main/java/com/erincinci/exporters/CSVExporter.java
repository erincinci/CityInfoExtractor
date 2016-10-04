package com.erincinci.exporters;

import com.erincinci.models.goeuro.City;
import com.erincinci.rest.IGoEuro;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV Exporter Class
 * Created by erincinci on 19/09/16.
 */
@Service("csvExporter")
public class CSVExporter implements IExporter<String, Boolean> {
    // Attributes
    private static final Logger logger = LoggerFactory.getLogger(CSVExporter.class);
    private IGoEuro goEuro;

    // Properties
    @Value("${goeuro.api.url}")
    private String goEuroApiUrl;
    @Value("${goeuro.api.lang}")
    private String goEuroApiLanguage;
    @Value("${output.csv.filename}")
    private String outputFilename;

    /**
     * Initialize
     */
    @PostConstruct
    public void init() {
        // Initialize GoEuro API Client
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        goEuro = Feign.builder()
                .encoder(new JacksonEncoder(mapper))
                .decoder(new JacksonDecoder(mapper))
                .target(IGoEuro.class, goEuroApiUrl);
    }

    /**
     * Export City Suggestions to CSV File
     * @param cityName
     * @return
     */
    public Boolean export(String cityName) {
        // Get city suggestions
        List<City> cities;
        try {
            cities = goEuro.suggestCity(goEuroApiLanguage, cityName);
        } catch (FeignException e) {
            logger.error("HTTP exception while getting suggestions! " + e);
            return false;
        }

        // Check for empty results
        if (cities.isEmpty()) {
            logger.warn("No suggestions found from GoEuro API for city '" + cityName + "'");
            return false;
        }

        // Prepare CSV Lines (Can be converted to Jackson CSV Mapper)
        List<String> csvLines = new ArrayList<>();
        for (City city : cities)
            csvLines.add(city.toCsvLine());

        // Write data to .csv file (overwrite if exists)
        Path filePath = Paths.get(outputFilename);
        try {
            Files.write(
                    filePath,
                    csvLines,
                    Charset.forName("UTF-8"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            return true;
        } catch (IOException e) {
            logger.error("IO exception while writing data to CSV file! " + e);
            return false;
        }
    }
}
