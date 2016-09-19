package com.erincinci;

import com.erincinci.models.goeuro.City;
import com.erincinci.rest.IGoEuro;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Tests
 * Created by erincinci on 19.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CityInfoExtractorTest {
    // Attributes
    private IGoEuro goEuro;

    // Properties
    @Value("${goeuro.api.url}")
    private String goEuroApiUrl;
    @Value("${goeuro.api.lang}")
    private String goEuroApiLanguage;

    @Before
    public void setup() throws Exception {
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

    @Test
    public void testEmptyCitySuggestion() throws Exception {
        List<City> cities = goEuro.suggestCity(goEuroApiLanguage, "Invalid City");
        Assert.assertTrue(cities.isEmpty());
    }

    @Test
    public void testAnkaraSuggestions() throws Exception {
        List<City> cities = goEuro.suggestCity(goEuroApiLanguage, "Ankara");
        Assert.assertTrue(cities.size() == 3);
    }

    // TODO: Mapper and CSV exporter tests
}
