public class PatientMonitoringSystem {
    public static void main(String[] args) {
        System.out.print("Hello World!");
    }

    public void displayMessage(String displayedMessage) {
        screen.display(displayedMessage);
    }

    private MessageDisplayer screen = new Screen();
}
