package com.erincinci.models.goeuro;

import com.erincinci.models.BaseModel;

/**
 * GoEuro City Model
 * Created by erincinci on 19.09.2016.
 */
public class GeoPosition extends BaseModel {
    // Attributes
    private float latitude;
    private float longitude;

    /**
     * Constructors
     */
    public GeoPosition() {
    }

    public GeoPosition(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoPosition(long id, float latitude, float longitude) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getters & Setters
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toCsvLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(latitude).append(',').append(longitude);
        return sb.toString();
    }
}
