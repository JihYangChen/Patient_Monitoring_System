import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemSetterTest {

    @Test
    void initializeSystem() {
        PatientMonitoringSystem system = new PatientMonitoringSystem();
        SystemSetter systemSetter = new FileSystemSetter(system, "sampleInput");
        systemSetter.initializeSystem();
        system.startMonitoring();
    }
}