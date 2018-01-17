public abstract class SystemSetter {
    public abstract void initializeSystem();

    public void setMonitorPeriod(int time) {
        _patientMonitoringSystem.setMonitorPeriod(time);
    }
    public void addPatient(Patient patient) {
        _patientMonitoringSystem.addPatient(patient);
    }

    public void addDevice(Device device) {
        _patientMonitoringSystem.addDevice(device);
    }

    protected PatientMonitoringSystem _patientMonitoringSystem;
}
