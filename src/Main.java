import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(age -> age.getAge() > 18)
                .count();

        System.out.println(count);

        List<String> recruits = persons.stream()
                .filter(sex -> sex.getSex().equals(Sex.MAN))
                .filter(age -> age.getAge() >= 18 && age.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println(recruits);

        List<String> potentialEmployee = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(age -> age.getAge() >= 18)
                .filter(
                        person -> ((person.getSex() == Sex.MAN) && (person.getAge() < 65))
                        &&
                        ((person.getSex() == Sex.WOMAN) && (person.getAge() < 60))
                        )
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::toString)
                .collect(Collectors.toList());

        System.out.println(potentialEmployee);
    }
}