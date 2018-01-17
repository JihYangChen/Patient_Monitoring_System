import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatasetFile implements FactorsProvider {
    public DatasetFile(String fileName) {
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                _factorArrayList.add(Double.parseDouble(line));
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.toString());
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public double getFactor() {
        if (_currentFactorIndex == _factorArrayList.size())
            return -1;
        return _factorArrayList.get(_currentFactorIndex ++);
    }

    int _currentFactorIndex = 0;
    ArrayList<Double> _factorArrayList = new ArrayList<>();
}
