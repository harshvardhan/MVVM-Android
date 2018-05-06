package dailydomain.test.com.sgpowermap.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys="timestamp")
public class Item {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("update_timestamp")
    @Expose
    private String updateTimestamp;
    @SerializedName("readings")
    @Expose
    private Readings readings;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Item withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public Item withUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
        return this;
    }

    public Readings getReadings() {
        return readings;
    }

    public void setReadings(Readings readings) {
        this.readings = readings;
    }

    public Item withReadings(Readings readings) {
        this.readings = readings;
        return this;
    }
}
