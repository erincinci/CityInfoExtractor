package com.erincinci.exporters;

import com.erincinci.models.goeuro.City;
import com.erincinci.rest.IGoEuro;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        List<City> cities = goEuro.suggestCity(goEuroApiLanguage, cityName);

        // TODO: Check for empty results

        // TODO: Export to CSV file

        for (City city : cities)
            logger.info(city.toString());
        return true;
    }
}
