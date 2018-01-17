public class BloodPressureSensor extends Device {
    public BloodPressureSensor(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "BloodPressureSensor";
    }
}
