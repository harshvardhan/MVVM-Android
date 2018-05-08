package dailydomain.test.com.sgpowermap.vo.readings;

public class CoSubIndex extends BaseReadings{
    @Override
    public void setReadingValuesMap() {
        westReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getWest()));
        eastReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getEast()));
        northReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getNorth()));
        southReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getSouth()));
        centralReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getCentral()));
        nationalReadings.put(this.getClass().getSimpleName(), String.valueOf(this.getNational()));
    }
}
