import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatasetFileTest {
    @Test
    void getFactor() {
        double[] _factors = {150, 123, -1, 200, -1};
        FactorsProvider dataset = new DatasetFile("BloodPressureData1.dataset");
        for (int i=0; i<_factors.length; i++)
            assertEquals(_factors[i], dataset.getFactor());
    }
}