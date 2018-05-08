package dailydomain.test.com.sgpowermap.vo.readings;

import java.util.HashMap;

public interface IBaseReadings {
    HashMap<String, String> westReadings = new HashMap<>();
    HashMap<String, String> eastReadings = new HashMap<>();
    HashMap<String, String> northReadings = new HashMap<>();
    HashMap<String, String> southReadings = new HashMap<>();
    HashMap<String, String> centralReadings = new HashMap<>();
    HashMap<String, String> nationalReadings = new HashMap<>();

    HashMap<String, String> getReadingValuesMapInWest();
    HashMap<String, String> getReadingValuesMapInEast();
    HashMap<String, String> getReadingValuesMapInNorth();
    HashMap<String, String> getReadingValuesMapInSouth();
    HashMap<String, String> getReadingValuesMapInCentral();
    HashMap<String, String> getReadingValuesMapNational();
}
