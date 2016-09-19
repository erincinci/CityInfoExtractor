package com.erincinci;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests
 * Created by erincinci on 19.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CityInfoExtractorTest {
    @Before
    public void setup() throws Exception {
        // TODO
    }

    @Test
    public void testEmptyCitySuggestion() throws Exception {
        // TODO
    }
}
