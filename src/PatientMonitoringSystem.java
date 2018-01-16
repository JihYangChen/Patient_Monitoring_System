import java.util.ArrayList;

public class PatientMonitoringSystem {

    public void startMonitoring() {
        for (int i=0; i<=_monitorPeriod; i++) {
            for (Device device : _deviceArrayList) {
                device.onClockEvent(i);
            }
        }
    }

    public void displayMessage(String displayedMessage) {
        screen.display(displayedMessage);
    }

    public void setMonitorPeriod(int monitorPeriod) {
        _monitorPeriod = monitorPeriod;
    }

    public void addDevice(Device device) {
        _deviceArrayList.add(device);
    }

    private int _monitorPeriod;
    private ArrayList<Device> _deviceArrayList = new ArrayList<>();
    private MessageDisplayer screen = new Screen();
}
