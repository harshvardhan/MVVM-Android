package dailydomain.test.com.sgpowermap.vo.readings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public abstract class BaseReadings implements IBaseReadings{
    @SerializedName("west")
    @Expose
    private double west;
    @SerializedName("national")
    @Expose
    private double national;
    @SerializedName("east")
    @Expose
    private double east;
    @SerializedName("central")
    @Expose
    private double central;
    @SerializedName("south")
    @Expose
    private double south;
    @SerializedName("north")
    @Expose
    private double north;

    public double getWest() {
        return this.west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public double getNational() {
        return this.national;
    }

    public void setNational(double national) {
        this.national = national;
    }

    public double getEast() {
        return this.east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public double getCentral() {
        return this.central;
    }

    public void setCentral(double central) {
        this.central = central;
    }

    public double getSouth() {
        return this.south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public double getNorth() {
        return this.north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public abstract void setReadingValuesMap();

    @Override
    public HashMap<String, String> getReadingValuesMapInWest() {
        return westReadings;
    }

    @Override
    public HashMap<String, String> getReadingValuesMapInEast() {
        return eastReadings;
    }

    @Override
    public HashMap<String, String> getReadingValuesMapInNorth() {
        return northReadings;
    }

    @Override
    public HashMap<String, String> getReadingValuesMapInSouth() {
        return southReadings;
    }

    @Override
    public HashMap<String, String> getReadingValuesMapInCentral() {
        return centralReadings;
    }

    @Override
    public HashMap<String, String> getReadingValuesMapNational() {
        return nationalReadings;
    }
}
