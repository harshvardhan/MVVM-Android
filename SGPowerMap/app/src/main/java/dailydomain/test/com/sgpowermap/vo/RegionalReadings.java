package dailydomain.test.com.sgpowermap.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class RegionalReadings {
    private String region;
    private double latitude;
    private double longitude;
    private HashMap<String, String> readingsMap;

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public HashMap<String, String> getReadingsMap() {
        return this.readingsMap;
    }

    public void setReadingsMap(HashMap<String, String> readingsMap) {
        this.readingsMap = readingsMap;
    }
}
