
public interface Database {
    void addPatient(String name);
    void addDeviceToPatient(String patientName, String deviceCategory, String deviceName);
    void addMeasureLog(String patientName, String deviceName, int time, double factor);
}
