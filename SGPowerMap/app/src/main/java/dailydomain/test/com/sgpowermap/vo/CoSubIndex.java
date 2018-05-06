package dailydomain.test.com.sgpowermap.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoSubIndex {
    @SerializedName("west")
    @Expose
    private int west;
    @SerializedName("national")
    @Expose
    private int national;
    @SerializedName("east")
    @Expose
    private int east;
    @SerializedName("central")
    @Expose
    private int central;
    @SerializedName("south")
    @Expose
    private int south;
    @SerializedName("north")
    @Expose
    private int north;

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public CoSubIndex withWest(int west) {
        this.west = west;
        return this;
    }

    public int getNational() {
        return national;
    }

    public void setNational(int national) {
        this.national = national;
    }

    public CoSubIndex withNational(int national) {
        this.national = national;
        return this;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public CoSubIndex withEast(int east) {
        this.east = east;
        return this;
    }

    public int getCentral() {
        return central;
    }

    public void setCentral(int central) {
        this.central = central;
    }

    public CoSubIndex withCentral(int central) {
        this.central = central;
        return this;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public CoSubIndex withSouth(int south) {
        this.south = south;
        return this;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public CoSubIndex withNorth(int north) {
        this.north = north;
        return this;
    }
}
