import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    void onClockEvent() {
        Device bloodPressureSensor = new BloodPressureSensor("sensor1");
        bloodPressureSensor.setPatient(new Patient("Mark", 600));
        bloodPressureSensor.setSafeLowerBound(150);
        bloodPressureSensor.setSafeUpperBound(200);
        bloodPressureSensor.setFactorsProvider(new DatasetFile("BloodPressureData1.dataset"));
        bloodPressureSensor.setPatientMonitoringSystem(new PatientMonitoringSystem());

        for (int i = 0; i <= 3000 ; i++)
            bloodPressureSensor.onClockEvent(i);
    }
}