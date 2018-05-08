package dailydomain.test.com.sgpowermap.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import dailydomain.test.com.sgpowermap.vo.readings.CoEightHourMax;
import dailydomain.test.com.sgpowermap.vo.readings.CoSubIndex;
import dailydomain.test.com.sgpowermap.vo.readings.No2OneHourMax;
import dailydomain.test.com.sgpowermap.vo.readings.O3EightHourMax;
import dailydomain.test.com.sgpowermap.vo.readings.O3SubIndex;
import dailydomain.test.com.sgpowermap.vo.readings.Pm10SubIndex;
import dailydomain.test.com.sgpowermap.vo.readings.Pm10TwentyFourHourly;
import dailydomain.test.com.sgpowermap.vo.readings.Pm25SubIndex;
import dailydomain.test.com.sgpowermap.vo.readings.Pm25TwentyFourHourly;
import dailydomain.test.com.sgpowermap.vo.readings.PsiTwentyFourHourly;
import dailydomain.test.com.sgpowermap.vo.readings.So2SubIndex;
import dailydomain.test.com.sgpowermap.vo.readings.So2TwentyFourHourly;

public class Readings {
    @SerializedName("o3_sub_index")
    @Expose
    private O3SubIndex o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    private Pm10TwentyFourHourly pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    @Expose
    private Pm10SubIndex pm10SubIndex;
    @SerializedName("co_sub_index")
    @Expose
    private CoSubIndex coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    private Pm25TwentyFourHourly pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    @Expose
    private So2SubIndex so2SubIndex;
    @SerializedName("co_eight_hour_max")
    @Expose
    private CoEightHourMax coEightHourMax;
    @SerializedName("no2_one_hour_max")
    @Expose
    private No2OneHourMax no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    private So2TwentyFourHourly so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    @Expose
    private Pm25SubIndex pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    @Expose
    private PsiTwentyFourHourly psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    @Expose
    private O3EightHourMax o3EightHourMax;

    private HashMap<String, String> westReadings = new HashMap<>();
    private HashMap<String, String> eastReadings = new HashMap<>();
    private HashMap<String, String> northReadings = new HashMap<>();
    private HashMap<String, String> southReadings = new HashMap<>();
    private HashMap<String, String> centralReadings = new HashMap<>();
    private HashMap<String, String> nationalReadings = new HashMap<>();

    public O3SubIndex getO3SubIndex() {
        return o3SubIndex;
    }

    public void setO3SubIndex(O3SubIndex o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    public Readings withO3SubIndex(O3SubIndex o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
        return this;
    }

    public Pm10TwentyFourHourly getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(Pm10TwentyFourHourly pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public Readings withPm10TwentyFourHourly(Pm10TwentyFourHourly pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
        return this;
    }

    public Pm10SubIndex getPm10SubIndex() {
        return pm10SubIndex;
    }

    public void setPm10SubIndex(Pm10SubIndex pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    public Readings withPm10SubIndex(Pm10SubIndex pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
        return this;
    }

    public CoSubIndex getCoSubIndex() {
        return coSubIndex;
    }

    public void setCoSubIndex(CoSubIndex coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    public Readings withCoSubIndex(CoSubIndex coSubIndex) {
        this.coSubIndex = coSubIndex;
        return this;
    }

    public Pm25TwentyFourHourly getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(Pm25TwentyFourHourly pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public Readings withPm25TwentyFourHourly(Pm25TwentyFourHourly pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
        return this;
    }

    public So2SubIndex getSo2SubIndex() {
        return so2SubIndex;
    }

    public void setSo2SubIndex(So2SubIndex so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    public Readings withSo2SubIndex(So2SubIndex so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
        return this;
    }

    public CoEightHourMax getCoEightHourMax() {
        return coEightHourMax;
    }

    public void setCoEightHourMax(CoEightHourMax coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    public Readings withCoEightHourMax(CoEightHourMax coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
        return this;
    }

    public No2OneHourMax getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public void setNo2OneHourMax(No2OneHourMax no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    public Readings withNo2OneHourMax(No2OneHourMax no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
        return this;
    }

    public So2TwentyFourHourly getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public void setSo2TwentyFourHourly(So2TwentyFourHourly so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    public Readings withSo2TwentyFourHourly(So2TwentyFourHourly so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
        return this;
    }

    public Pm25SubIndex getPm25SubIndex() {
        return pm25SubIndex;
    }

    public void setPm25SubIndex(Pm25SubIndex pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    public Readings withPm25SubIndex(Pm25SubIndex pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
        return this;
    }

    public PsiTwentyFourHourly getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(PsiTwentyFourHourly psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public Readings withPsiTwentyFourHourly(PsiTwentyFourHourly psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
        return this;
    }

    public O3EightHourMax getO3EightHourMax() {
        return o3EightHourMax;
    }

    public void setO3EightHourMax(O3EightHourMax o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }

    public Readings withO3EightHourMax(O3EightHourMax o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
        return this;
    }

    public void setRegionalReadingDictionary() {
        this.coEightHourMax.setReadingValuesMap();
        this.coSubIndex.setReadingValuesMap();
        this.no2OneHourMax.setReadingValuesMap();
        this.o3EightHourMax.setReadingValuesMap();
        this.o3SubIndex.setReadingValuesMap();
        this.pm10SubIndex.setReadingValuesMap();
        this.pm10TwentyFourHourly.setReadingValuesMap();
        this.pm25SubIndex.setReadingValuesMap();
        this.pm25TwentyFourHourly.setReadingValuesMap();
        this.psiTwentyFourHourly.setReadingValuesMap();
        this.so2SubIndex.setReadingValuesMap();
        this.so2TwentyFourHourly.setReadingValuesMap();

        this.eastReadings = this.coEightHourMax.getReadingValuesMapInEast();
        this.westReadings = this.coEightHourMax.getReadingValuesMapInWest();
        this.northReadings = this.coEightHourMax.getReadingValuesMapInNorth();
        this.southReadings = this.coEightHourMax.getReadingValuesMapInSouth();
        this.centralReadings = this.coEightHourMax.getReadingValuesMapInCentral();
        this.nationalReadings = this.coEightHourMax.getReadingValuesMapNational();
    }

    public HashMap<String, String> getWestReadings() {
        return this.westReadings;
    }

    public void setWestReadings(HashMap<String, String> westReadings) {
        this.westReadings = westReadings;
    }

    public HashMap<String, String> getEastReadings() {
        return this.eastReadings;
    }

    public void setEastReadings(HashMap<String, String> eastReadings) {
        this.eastReadings = eastReadings;
    }

    public HashMap<String, String> getNorthReadings() {
        return this.northReadings;
    }

    public void setNorthReadings(HashMap<String, String> northReadings) {
        this.northReadings = northReadings;
    }

    public HashMap<String, String> getSouthReadings() {
        return this.southReadings;
    }

    public void setSouthReadings(HashMap<String, String> southReadings) {
        this.southReadings = southReadings;
    }

    public HashMap<String, String> getCentralReadings() {
        return this.centralReadings;
    }

    public void setCentralReadings(HashMap<String, String> centralReadings) {
        this.centralReadings = centralReadings;
    }

    public HashMap<String, String> getNationalReadings() {
        return this.nationalReadings;
    }

    public void setNationalReadings(HashMap<String, String> nationalReadings) {
        this.nationalReadings = nationalReadings;
    }
}
