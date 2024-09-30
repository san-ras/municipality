package lt.techin.municipality;

public class TaxCalculatorImpl implements TaxCalculator {

    private TaxRateProvider taxRateProvider;

    public TaxCalculatorImpl(TaxRateProvider taxRateProvider) {
        this.taxRateProvider = taxRateProvider;
    }

    @Override
    public double calculateTax(Person person) {
        return taxRateProvider.getTaxRate(person.getYearlyIncome()) * person.getYearlyIncome() / 100;
    }
}
