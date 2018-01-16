public interface SystemSetter {
    void initializeSystem();
    void setMonitorPeriod(int time);
    void addDevice(Device device);
}
