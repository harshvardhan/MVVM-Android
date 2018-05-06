package dailydomain.test.com.sgpowermap.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionMetadatum {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("label_location")
    @Expose
    private LabelLocation labelLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegionMetadatum withName(String name) {
        this.name = name;
        return this;
    }

    public LabelLocation getLabelLocation() {
        return labelLocation;
    }

    public void setLabelLocation(LabelLocation labelLocation) {
        this.labelLocation = labelLocation;
    }

    public RegionMetadatum withLabelLocation(LabelLocation labelLocation) {
        this.labelLocation = labelLocation;
        return this;
    }
}
