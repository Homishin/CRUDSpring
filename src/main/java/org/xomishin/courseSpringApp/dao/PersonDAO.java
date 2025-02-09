package org.xomishin.courseSpringApp.dao;

import org.springframework.stereotype.Component;
import org.xomishin.courseSpringApp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_ID;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PERSON_ID,"Jone"));
        people.add(new Person(++PERSON_ID,"Tom"));
        people.add(new Person(++PERSON_ID,"Mike"));
        people.add(new Person(++PERSON_ID,"Bob"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
}
