package lt.techin.municipality;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MunicipalityImpl implements Municipality {

    private Set<Person> registry;
    private TaxCalculator taxCalculator;

    public MunicipalityImpl(TaxCalculator taxCalculator) {
        this.registry = new HashSet<>();
        this.taxCalculator = taxCalculator;
    }

    @Override
    public void registerCitizen(Person person) throws IllegalCitizenException {
        if (person.getFirstName() == null || person.getLastName() == null || person.getDateOfBirth() == null || person.getYearlyIncome() < 0 || person.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalCitizenException(person);
        } else {
            registry.add(person);
        }
    }

    @Override
    public int getCitizenCount() {
        return registry.size();
    }

    @Override
    public double getAverageCitizenIncome() {
        return registry.stream().mapToDouble(Person::getYearlyIncome).average().orElse(0.0);
    }

    @Override
    public boolean isRegisteredCitizen(Person person) {
        return registry.stream().anyMatch(addedPerson -> addedPerson.equals(person));
    }

    @Override
    public Person findOldestPerson() {
        return registry.stream().min(Comparator.comparing(Person::getDateOfBirth)).orElse(null);
    }

    @Override
    public int countAdultCitizens() {
        return (int) registry.stream().filter(p -> p.getDateOfBirth().isBefore(LocalDate.now().minusYears(18))).count();
    }

    @Override
    public double totalIncomeInTaxes() {
        return registry.stream().mapToDouble(p -> taxCalculator.calculateTax(p)).sum();
    }

    @Override
    public Collection<Person> findCitizensBy(PersonPredicate personPredicate) {
       return registry.stream().filter(personPredicate::test).collect(Collectors.toList());
    }

    @Override
    public Collection<Person> getOrderedCitizens() {
        return registry.stream().sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Person>> groupByYearOfBirth() {
        return registry.stream().collect(Collectors.groupingBy(p -> p.getDateOfBirth().getYear()));
    }
}
