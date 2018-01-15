public class Patient {

    public Patient(String name, int monitorPeriod) {
        _name = name;
        _monitorPeriod = monitorPeriod;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getMonitorPeriod() {
        return _monitorPeriod;
    }

    public void setMonitorPeriod(int monitorPeriod) {
        _monitorPeriod = monitorPeriod;
    }

    private String _name;
    private int _monitorPeriod;
}
