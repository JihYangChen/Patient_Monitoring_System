public class Main {
    public static void main(String[] args) {
        PatientMonitoringSystem system = new PatientMonitoringSystem();
        SystemSetter systemSetter = new FileSystemSetter(system, args[0]);
        systemSetter.initializeSystem();
        system.startMonitoring();
    }
}
