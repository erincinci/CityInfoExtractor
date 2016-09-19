package com.erincinci.models.goeuro;

import com.erincinci.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * GoEuro City Model
 * Created by erincinci on 19.09.2016.
 */
public class City extends BaseModel {
    // Attributes
    private String name;
    private String fullName;
    @JsonProperty("iata_airport_code") private String iataAirportCode;
    private String type; // TODO: can be converted to API after examining API documentation
    private String country;
    @JsonProperty("geo_position") private GeoPosition geoPosition;
    private Long locationId;
    private boolean inEurope;
    private Integer countryId;
    private String countryCode;
    private boolean coreCountry;

    /**
     * Constructors
     */
    public City() {
    }

    public City(String name, String fullName, String iataAirportCode, String type, String country, GeoPosition geoPosition, Long locationId, boolean inEurope, Integer countryId, String countryCode, boolean coreCountry) {
        this.name = name;
        this.fullName = fullName;
        this.iataAirportCode = iataAirportCode;
        this.type = type;
        this.country = country;
        this.geoPosition = geoPosition;
        this.locationId = locationId;
        this.inEurope = inEurope;
        this.countryId = countryId;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
    }

    public City(long id, String name, String fullName, String iataAirportCode, String type, String country, GeoPosition geoPosition, Long locationId, boolean inEurope, Integer countryId, String countryCode, boolean coreCountry) {
        super(id);
        this.name = name;
        this.fullName = fullName;
        this.iataAirportCode = iataAirportCode;
        this.type = type;
        this.country = country;
        this.geoPosition = geoPosition;
        this.locationId = locationId;
        this.inEurope = inEurope;
        this.countryId = countryId;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
    }

    /**
     * Overriden Methods
     */
    @Override
    @JsonProperty("_id")
    public void setId(long id) {
        super.setId(id);
    }

    /**
     * Auxiliary Methods
     */
    public String toCsvLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(',')
                .append(this.name).append(',')
                .append(this.type).append(',')
                .append(this.geoPosition.toCsvLine());
        return sb.toString();
    }

    /**
     * Getters & Setters
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIataAirportCode() {
        return iataAirportCode;
    }

    public void setIataAirportCode(String iataAirportCode) {
        this.iataAirportCode = iataAirportCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public boolean isInEurope() {
        return inEurope;
    }

    public void setInEurope(boolean inEurope) {
        this.inEurope = inEurope;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isCoreCountry() {
        return coreCountry;
    }

    public void setCoreCountry(boolean coreCountry) {
        this.coreCountry = coreCountry;
    }
}
