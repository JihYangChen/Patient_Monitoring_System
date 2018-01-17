import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSystemSetter extends SystemSetter {

    public FileSystemSetter(PatientMonitoringSystem system, String fileName) {
        this._patientMonitoringSystem = system;
        this._fileName = fileName;
    }

    @Override
    public void initializeSystem() {
        String line;
        try {
            FileReader fileReader = new FileReader(_fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            setMonitorPeriod(Integer.parseInt(line));

            Patient patient = null;
            while((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (splitLine[0].equals("patient")) {
                    patient = createPatient(splitLine);
                    addPatient(patient);
                }
                else {
                    Device device = createDevice(splitLine);
                    device.setPatient(patient);
                    addDevice(device);
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.toString());
            System.out.println("Unable to open file '" + _fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private Patient createPatient(String[] splitLine) {
        return new Patient(splitLine[1], Integer.parseInt(splitLine[2]));
    }

    private Device createDevice(String[] splitLine) {
        Device device = null;
        if (splitLine[0].equals("BloodPressureSensor"))
            device = new BloodPressureSensor(splitLine[1]);
        else if (splitLine[0].equals("PulseSensor"))
            device = new PulseSensor(splitLine[1]);
        else if (splitLine[0].equals("PulseSensor"))
            device = new TemperatureSensor(splitLine[1]);

        device.setFactorsProvider(new DatasetFile(splitLine[2]));
        device.setSafeLowerBound(Double.parseDouble(splitLine[3]));
        device.setSafeUpperBound(Double.parseDouble(splitLine[4]));
        device.setPatientMonitoringSystem(_patientMonitoringSystem);

        return device;
    }

    private String _fileName;
}
