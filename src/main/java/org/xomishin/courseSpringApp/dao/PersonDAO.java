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

        people.add(new Person(++PERSON_ID,"Jone",27,"Jone@mail.ru"));
        people.add(new Person(++PERSON_ID,"Tom",24,"Tom@mail.ru"));
        people.add(new Person(++PERSON_ID,"Mike", 31,"Mike@bk.ru"));
        people.add(new Person(++PERSON_ID,"Bob", 56,"Bob@goole.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PERSON_ID);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person old = show(id);
        old.setName(person.getName());
        old.setAge(person.getAge());
        old.setEmail(person.getEmail());
    }

    public void remove(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
