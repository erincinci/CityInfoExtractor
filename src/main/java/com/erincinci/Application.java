package com.erincinci;

import com.erincinci.exporters.IExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Spring Boot Application Class
 * Created by erincinci on 19.09.2016.
 */
@SpringBootApplication
public class Application {
    // Attributes
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static enum EXIT_CODES { SUCCESS, NO_ARGS, INVALID_ARGS, CSV_ERR }
    private static String cityName;

    @Autowired
    @Qualifier("csvExporter")
    private IExporter<String, Boolean> csvExporter;

    /**
     * Post Construction
     */
    @PostConstruct
    public void init() {
        // Check suggestions and convert to CSV file
        if (csvExporter.export(cityName)) {
            logger.info("Exported to CSV!");
        } else {
            logger.error("There was an error converting to CSV!");
            System.exit(EXIT_CODES.CSV_ERR.ordinal());
        }
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        // Check for program arguments
        if (args.length != 1) {
            logger.error("Please provide a single program argument: CITY_NAME");
            System.exit(EXIT_CODES.NO_ARGS.ordinal());
        }
        if (args[0].isEmpty()) {
            logger.warn("Please provide a valid CITY_NAME!");
            System.exit(EXIT_CODES.INVALID_ARGS.ordinal());
        }

        cityName = args[0];
        SpringApplication.run(Application.class, args);
    }
}
