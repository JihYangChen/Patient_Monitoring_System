import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseFile implements Database {

    @Override
    public void addPatient(String name) {
        patientMeasureLogDB.put(new JSONObject(String.format("{\"name\":\"%s\", \"device\":[]}", name)));
    }

    @Override
    public void addDeviceToPatient(String patientName, String deviceCategory, String deviceName) {
        for (int i=0; i<patientMeasureLogDB.length(); i++) {
            JSONObject patient = patientMeasureLogDB.getJSONObject(i);
            if (patient.getString("name").equals(patientName)) {
                patient.getJSONArray("device").put(new JSONObject(
                        String.format("{\"name\":\"%s\", \"category\":\"%s\", \"measureLog\":[]}", deviceName, deviceCategory)));
                return;
            }
        }
    }

    @Override
    public void addMeasureLog(String patientName, String deviceName, int time, double factor) {
        for (int i=0; i<patientMeasureLogDB.length(); i++) {
            JSONObject patient = patientMeasureLogDB.getJSONObject(i);
            if (patient.getString("name").equals(patientName)) {
                JSONArray devices = patient.getJSONArray("device");
                for (int j=0; j<devices.length(); j++) {
                    JSONObject device = devices.getJSONObject(j);
                    if (device.getString("name").equals(deviceName)) {
                        device.getJSONArray("measureLog").put(new JSONObject(String.format("{\"time\":%d, \"factor\":%.1f}", time, factor)));
                        return;
                    }
                }
            }
        }
    }

    public String getFormattedDBString() {
        StringBuilder formattedStringBuilder = new StringBuilder();
        for (int i=0; i<patientMeasureLogDB.length(); i++) {
            JSONObject patient = patientMeasureLogDB.getJSONObject(i);
            formattedStringBuilder.append(String.format("patient %s\n", patient.getString("name")));
            JSONArray devices = patient.getJSONArray("device");
            for (int j=0; j<devices.length(); j++) {
                JSONObject device = devices.getJSONObject(j);
                formattedStringBuilder.append(String.format("%s %s\n", device.getString("category"), device.getString("name")));
                JSONArray measureLogs = device.getJSONArray("measureLog");
                for (int k=0; k<measureLogs.length(); k++) {
                    JSONObject measureLog = measureLogs.getJSONObject(k);
                    formattedStringBuilder.append(String.format("[%s] %.1f\n", measureLog.getInt("time"), measureLog.getDouble("factor")));
                }
            }
        }
        return formattedStringBuilder.toString();
    }

    public String getJSONDBString() {
        return patientMeasureLogDB.toString();
    }

    private JSONArray patientMeasureLogDB = new JSONArray("[]");
}
