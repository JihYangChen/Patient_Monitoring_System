public class DatasetFile implements FactorsProvider {
    public DatasetFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public double getFactor() {
        return _factors[_currentFactorIndex ++];
    }

    String fileName;
    int _currentFactorIndex = 0;
    double[] _factors = {150, 123, -1, 200, -1};
}
