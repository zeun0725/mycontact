package com.fastcampus.javaallinone.project.mycontact.repository;

import com.fastcampus.javaallinone.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("jieun");
        person.setAge(27);
        person.setBloodType("AB");

        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("jieun");
        assertThat(people.get(0).getAge()).isEqualTo(27);
        assertThat(people.get(0).getBloodType()).isEqualTo("AB");

    }

    @Test
    void constructorTest() {
        Person person = new Person("martine", 10);
    }

    @Test
    void hashCodeAndEquals() {
        Person person1 = new Person("martine", 10);
        Person person2 = new Person("martine", 10);

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());
        System.out.println(map);
        System.out.println(map.get(person2));
    }

}