package com.erincinci.rest;

import com.erincinci.models.goeuro.City;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * GoEuro REST API Interface Definition
 * Created by erincinci on 19.09.2016.
 */
public interface IGoEuro {
    // Suggest City from Name
    @RequestLine("GET /api/v2/position/suggest/{language}/{city_name}")
    List<City> suggestCity(@Param("language") String language, @Param("city_name") String cityName);
}
