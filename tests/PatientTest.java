import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Patient patient = new Patient("Mark", 600);

    @Test
    void getName() {
        assertEquals("Mark", patient.getName());
    }

    @Test
    void setName() {
        patient.setName("Mary");
        assertEquals("Mary", patient.getName());
    }

    @Test
    void getMonitorPeriod() {
        assertEquals(600, patient.getMonitorPeriod());
    }

    @Test
    void setMonitorPeriod() {
        patient.setMonitorPeriod(300);
        assertEquals(300, patient.getMonitorPeriod());
    }
}