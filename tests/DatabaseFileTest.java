import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFileTest {

    @Test
    void addPatient() {
        DatabaseFile db = new DatabaseFile();
        db.addPatient("Mark");
        JSONAssert.assertEquals("[{\"name\":\"Mark\",\"device\":[]}]", new JSONArray(db.getJSONDBString()), true);
        db.addPatient("Tony");
        JSONAssert.assertEquals("[{\"name\":\"Mark\",\"device\":[]},{\"name\":\"Tony\",\"device\":[]}]", new JSONArray(db.getJSONDBString()), true);
    }

    @Test
    void addDeviceToPatient() {
        DatabaseFile db = new DatabaseFile();
        db.addPatient("Mark");
        db.addPatient("Tony");
        db.addDeviceToPatient("Mark", "BloodPressureSensor", "sensor1");
        JSONAssert.assertEquals("[{\"name\":\"Mark\",\"device\":[" +
                                                                                "{" +
                                                                                    "\"name\":\"sensor1\"," +
                                                                                    "\"category\":\"BloodPressureSensor\"," +
                                                                                    "\"measureLog\":[]" +
                                                                                "}" +
                                                                            "]" +
                                "},{\"name\":\"Tony\",\"device\":[]}]", new JSONArray(db.getJSONDBString()), true);
    }

    @Test
    void addMeasureLog() {
        DatabaseFile db = new DatabaseFile();
        db.addPatient("Mark");
        db.addPatient("Tony");
        db.addDeviceToPatient("Mark", "BloodPressureSensor", "sensor1");
        db.addDeviceToPatient("Tony", "BloodPressureSensor", "sensor2");
        db.addMeasureLog("Mark", "sensor1", 0, 150);
        db.addMeasureLog("Mark", "sensor1", 600, 123);
        db.addMeasureLog("Mark", "sensor1", 1200, -1);
        db.addMeasureLog("Tony", "sensor2", 0, 151);
        db.addMeasureLog("Tony", "sensor2", 600, 124);
        db.addMeasureLog("Tony", "sensor2", 1200, -1);
        JSONAssert.assertEquals("[{\"name\":\"Mark\",\"device\":[" +
                                                                    "{" +
                                                                        "\"name\":\"sensor1\"," +
                                                                        "\"category\":\"BloodPressureSensor\"," +
                                                                        "\"measureLog\":[" +
                                                                                            "{\"time\":0, \"factor\":150}," +
                                                                                            "{\"time\":600, \"factor\":123}," +
                                                                                            "{\"time\":1200, \"factor\":-1}" +
                                                                                        "]" +
                                                                    "}" +
                                                                "]" +
                                "},{\"name\":\"Tony\",\"device\":[ "+
                                                                                "{" +
                                                                                    "\"name\":\"sensor2\"," +
                                                                                    "\"category\":\"BloodPressureSensor\"," +
                                                                                    "\"measureLog\":[" +
                                                                                                        "{\"time\":0, \"factor\":151}," +
                                                                                                        "{\"time\":600, \"factor\":124}," +
                                                                                                        "{\"time\":1200, \"factor\":-1}" +
                                                                                                    "]" +
                                                                                "}" +
                                                                    "]" +
                                   "}]", new JSONArray(db.getJSONDBString()), true);
    }

    @Test
    void getFormattedDBString() {
                DatabaseFile db = new DatabaseFile();
        db.addPatient("Mark");
        db.addPatient("Tony");
        db.addDeviceToPatient("Mark", "BloodPressureSensor", "sensor1");
        db.addDeviceToPatient("Tony", "BloodPressureSensor", "sensor2");
        db.addMeasureLog("Mark", "sensor1", 0, 150);
        db.addMeasureLog("Mark", "sensor1", 600, 123);
        db.addMeasureLog("Mark", "sensor1", 1200, -1);
        db.addMeasureLog("Tony", "sensor2", 0, 151);
        db.addMeasureLog("Tony", "sensor2", 600, 124);
        db.addMeasureLog("Tony", "sensor2", 1200, -1);
        JSONAssert.assertEquals("[{\"name\":\"Mark\",\"device\":[" +
                                                                    "{" +
                                                                        "\"name\":\"sensor1\"," +
                                                                        "\"category\":\"BloodPressureSensor\"," +
                                                                        "\"measureLog\":[" +
                                                                                            "{\"time\":0, \"factor\":150}," +
                                                                                            "{\"time\":600, \"factor\":123}," +
                                                                                            "{\"time\":1200, \"factor\":-1}" +
                                                                                        "]" +
                                                                    "}" +
                                                                "]" +
                                "},{\"name\":\"Tony\",\"device\":[ "+
                                                                                "{" +
                                                                                    "\"name\":\"sensor2\"," +
                                                                                    "\"category\":\"BloodPressureSensor\"," +
                                                                                    "\"measureLog\":[" +
                                                                                                        "{\"time\":0, \"factor\":151}," +
                                                                                                        "{\"time\":600, \"factor\":124}," +
                                                                                                        "{\"time\":1200, \"factor\":-1}" +
                                                                                                    "]" +
                                                                                "}" +
                                                                    "]" +
                                   "}]", new JSONArray(db.getJSONDBString()), true);

        String expectedOutputString = "patient Mark\n" +
                "BloodPressureSensor sensor1\n" +
                "[0] 150.0\n" +
                "[600] 123.0\n" +
                "[1200] -1.0\n" +
                "patient Tony\n" +
                "BloodPressureSensor sensor2\n" +
                "[0] 151.0\n" +
                "[600] 124.0\n" +
                "[1200] -1.0";

        assertEquals(expectedOutputString, db.getFormattedDBString());
    }

}