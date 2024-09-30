package lt.techin.municipality;

import lt.techin.municipality.test.AbstractMunicipalityTest;

public class MunicipalityTest extends AbstractMunicipalityTest {
    @Override
    public Municipality getMunicipality(TaxCalculator taxCalculator) {
        return new MunicipalityImpl(taxCalculator);
    }

    @Override
    public TaxCalculator getTaxCalculator(TaxRateProvider taxRateProvider) {
        return new TaxCalculatorImpl(taxRateProvider);
    }
}
