public class PulseSensor extends Device {
    public PulseSensor(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "PulseSensor";
    }
}
