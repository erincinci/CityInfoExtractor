package com.erincinci;

import com.erincinci.exporters.IExporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Application jUnit Tests
 * Created by erincinci on 04/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class ApplicationTest {
    @Autowired
    @Qualifier("csvExporter")
    private IExporter<String, Boolean> csvExporter;

    @Test
    public void shouldFindSuggestions() throws Exception {
        assertTrue(csvExporter.export("Ankara"));
    }

    @Test
    public void shouldNotFindSuggestions() throws Exception {
        assertFalse(csvExporter.export("Hebele"));
    }
}