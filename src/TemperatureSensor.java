public class TemperatureSensor extends Device {
    public TemperatureSensor(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "TemperatureSensor";
    }
}
