package dailydomain.test.com.sgpowermap.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm25TwentyFourHourly {
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
        return west;
    }

    public void setWest(double west) {
        this.west = west;
    }

    public Pm25TwentyFourHourly withWest(double west) {
        this.west = west;
        return this;
    }

    public double getNational() {
        return national;
    }

    public void setNational(double national) {
        this.national = national;
    }

    public Pm25TwentyFourHourly withNational(double national) {
        this.national = national;
        return this;
    }

    public double getEast() {
        return east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public Pm25TwentyFourHourly withEast(double east) {
        this.east = east;
        return this;
    }

    public double getCentral() {
        return central;
    }

    public void setCentral(double central) {
        this.central = central;
    }

    public Pm25TwentyFourHourly withCentral(double central) {
        this.central = central;
        return this;
    }

    public double getSouth() {
        return south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public Pm25TwentyFourHourly withSouth(double south) {
        this.south = south;
        return this;
    }

    public double getNorth() {
        return north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public Pm25TwentyFourHourly withNorth(double north) {
        this.north = north;
        return this;
    }
}
