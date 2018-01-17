public abstract class Device {
    public Device(String name) {
        this._name = name;
    }

    public abstract String getCategory();

    public void onClockEvent(int time) {
        if (isOnPatientPeriod(time)) {
            double measuredFactor = measure();
            if (isFall(measuredFactor))
                _patientMonitoringSystem.displayMessage(String.format("[%d] %s falls", time, _name));
            else if (isSafe(measuredFactor))
                _patientMonitoringSystem.displayMessage(String.format("[%d] %s is in danger! Cause: %s %.1f", time, _patient.getName(), _name, measuredFactor));
            _patientMonitoringSystem.storeMeasureLog(_patient.getName(), _name, time, measuredFactor);
        }
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Patient getPatient() {
        return _patient;
    }

    public void setPatient(Patient patient) {
        _patient = patient;
    }

    public double getSafeUpperBound() {
        return _safeUpperBound;
    }

    public void setSafeUpperBound(double safeUpperBound) {
        _safeUpperBound = safeUpperBound;
    }

    public double getSafeLowerBound() {
        return _safeLowerBound;
    }

    public void setSafeLowerBound(double safeLowerBound) {
        _safeLowerBound = safeLowerBound;
    }

    public void setFactorsProvider(FactorsProvider factorsProvider) {
        _factorsProvider = factorsProvider;
    }

    public void setPatientMonitoringSystem(PatientMonitoringSystem patientMonitoringSystem) {
        _patientMonitoringSystem = patientMonitoringSystem;
    }

    private double measure() {
        return _factorsProvider.getFactor();
    }

    private boolean isSafe(double measuredFactor) {
        return (measuredFactor < _safeLowerBound) || (measuredFactor > _safeUpperBound);
    }

    private boolean isFall(double measuredFactor) {
        return measuredFactor == -1;
    }

    private boolean isOnPatientPeriod(int time) {
        return (time % _patient.getMonitorPeriod()) == 0.00;
    }

    private String _name;
    private Patient _patient;
    private double _safeUpperBound;
    private double _safeLowerBound;
    private FactorsProvider _factorsProvider;
    private PatientMonitoringSystem _patientMonitoringSystem;
}
