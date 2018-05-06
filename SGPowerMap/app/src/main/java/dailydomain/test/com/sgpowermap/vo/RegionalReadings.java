package dailydomain.test.com.sgpowermap.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionalReadings {
    private String region;
    @SerializedName("o3_sub_index")
    @Expose
    private double o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    private double pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    @Expose
    private double pm10SubIndex;
    @SerializedName("co_sub_index")
    @Expose
    private double coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    private double pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    @Expose
    private double so2SubIndex;
    @SerializedName("co_eight_hour_max")
    @Expose
    private double coEightHourMax;
    @SerializedName("no2_one_hour_max")
    @Expose
    private double no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    private double so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    @Expose
    private double pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    @Expose
    private double psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    @Expose
    private double o3EightHourMax;
}
