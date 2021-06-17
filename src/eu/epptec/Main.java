package eu.epptec;

import eu.epptec.genericSetOperations.GenericSetOperations;
import eu.epptec.genericSetOperations.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people1 = new ArrayList<> ();
        people1.add(new Person("Vojtech", "Kulovany", 24));
        people1.add(new Person("Vojtech", "Kulovany", 24));
        people1.add(new Person("Zbysek", "Pantucek", 53));
        people1.add(new Person("Tomas", "Holy", 22));
        people1.add(new Person("Jan", "Novak", 38));
        people1.add(new Person("Jaromir", "Jagr", 48));

        List<Person> people2 = new ArrayList<> ();
        people2.add(new Person("Jaromir", "Jagr", 48));
        people2.add(new Person("Jaromir", "Jagr", 48));
        people2.add(new Person("Paul", "Simon", 79));
        people2.add(new Person("Art", "Garfunkel", 79));
        people2.add(new Person("Tomas", "Masaryk", 87));
        people2.add(new Person("Zdenek", "Kulovany", 63));

        Set<Person> people3 = new HashSet<>();
        people3.add(new Person("Jaromir", "Jagr", 48));
        people3.add(new Person("Paul", "Simon", 79));

        Stack<Person> people4 = new Stack<>();
        people4.add(new Person("Jaromir", "Jagr", 48));
        people4.add(new Person("Paul", "Simon", 79));

        Set<Person> people5 = new HashSet<>();

        System.out.println(GenericSetOperations.union(people1, people2));
        System.out.println(GenericSetOperations.difference(people1, people2));
        System.out.println(GenericSetOperations.intersection(people1, people2));
        System.out.println(GenericSetOperations.symmetricDifference(people1, people2));
        System.out.println(GenericSetOperations.symmetricDifference(people3, people4));
        System.out.println(GenericSetOperations.symmetricDifference(people1, people5));
        System.out.println(GenericSetOperations.symmetricDifference(people5, people1));
    }
}
