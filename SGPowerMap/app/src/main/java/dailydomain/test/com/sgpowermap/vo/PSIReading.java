package dailydomain.test.com.sgpowermap.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dailydomain.test.com.sgpowermap.db.SGPowerMapTypeConverters;

@Entity()
public class PSIReading {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("region_metadata")
    @Expose
    private List<RegionMetadatum> regionMetadata = null;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("api_info")
    @Expose
    private ApiInfo apiInfo;

    public List<RegionMetadatum> getRegionMetadata() {
        return regionMetadata;
    }

    public void setRegionMetadata(List<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public PSIReading withRegionMetadata(List<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PSIReading withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public ApiInfo getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    public PSIReading withApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
        return this;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
