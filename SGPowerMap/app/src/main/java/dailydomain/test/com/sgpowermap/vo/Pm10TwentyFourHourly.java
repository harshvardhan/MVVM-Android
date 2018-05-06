package dailydomain.test.com.sgpowermap.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity()
public class Pm10TwentyFourHourly {
    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public Pm10TwentyFourHourly withWest(double west) {
        this.west = west;
        return this;
    }

    public double getNational() {
        return national;
    }

    public void setNational(double national) {
        this.national = national;
    }

    public Pm10TwentyFourHourly withNational(double national) {
        this.national = national;
        return this;
    }

    public double getEast() {
        return east;
    }

    public void setEast(double east) {
        this.east = east;
    }

    public Pm10TwentyFourHourly withEast(double east) {
        this.east = east;
        return this;
    }

    public double getCentral() {
        return central;
    }

    public void setCentral(double central) {
        this.central = central;
    }

    public Pm10TwentyFourHourly withCentral(double central) {
        this.central = central;
        return this;
    }

    public double getSouth() {
        return south;
    }

    public void setSouth(double south) {
        this.south = south;
    }

    public Pm10TwentyFourHourly withSouth(double south) {
        this.south = south;
        return this;
    }

    public double getNorth() {
        return north;
    }

    public void setNorth(double north) {
        this.north = north;
    }

    public Pm10TwentyFourHourly withNorth(double north) {
        this.north = north;
        return this;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
