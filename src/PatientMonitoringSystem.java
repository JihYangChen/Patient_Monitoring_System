import java.util.ArrayList;

public class PatientMonitoringSystem {

    public void startMonitoring() {
        for (int i=0; i<=_monitorPeriod; i++) {
            for (Device device : _deviceArrayList) {
                device.onClockEvent(i);
            }
        }
        displayMessage(database.getFormattedDBString());
    }

    public void storeMeasureLog(String patientName, String deviceName, int time, double factor) {
        database.addMeasureLog(patientName, deviceName, time, factor);
    }

    public void displayMessage(String displayedMessage) {
        screen.display(displayedMessage);
    }

    public void setMonitorPeriod(int monitorPeriod) {
        _monitorPeriod = monitorPeriod;
    }

    public void addPatient(Patient patient) {
        _patientArrayList.add(patient);
        database.addPatient(patient.getName());
    }

    public void addDevice(Device device) {
        _deviceArrayList.add(device);
        database.addDeviceToPatient(device.getPatient().getName(), device.getCategory(), device.getName());
    }

    private int _monitorPeriod;
    private ArrayList<Patient> _patientArrayList = new ArrayList<>();
    private ArrayList<Device> _deviceArrayList = new ArrayList<>();
    private MessageDisplayer screen = new Screen();
    private Database database = new DatabaseFile();
}
