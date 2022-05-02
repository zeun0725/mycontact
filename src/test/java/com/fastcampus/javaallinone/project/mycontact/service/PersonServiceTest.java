package com.fastcampus.javaallinone.project.mycontact.service;

import com.fastcampus.javaallinone.project.mycontact.domain.Block;
import com.fastcampus.javaallinone.project.mycontact.domain.Person;
import com.fastcampus.javaallinone.project.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project.mycontact.repository.PersonRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        givenPeople();
        List<Person> result = personService.getPeopleExcludeBlocks();
        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName() {
        givenPeople();
        List<Person> result = personService.getPeopleByName("martin");
        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest() {
        givenPeople();
        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);
        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void findByBloodType() {
        givenPerson("martine", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 8, "O");
        givenPerson("saphia", 7, "AB");
        givenPerson("benny", 11, "A");

        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);
    }

    @Test
    void getPerson() {
        givenPeople();
        Person person = personService.getPerson(3L);
        System.out.println(person);
    }

    @Test
    void findByBirthdayBetween() {
        givenPerson("martine", 10, "A", LocalDate.of(1991,8,15));
        givenPerson("david", 9, "B", LocalDate.of(1992,8,30));
        givenPerson("dennis", 8, "O", LocalDate.of(1993,7,15));
        givenPerson("saphia", 7, "AB", LocalDate.of(1994,6,15));
        givenPerson("benny", 11, "A", LocalDate.of(1995,1,15));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1991, 8, 1), LocalDate.of(1995, 8, 31));
        result.forEach(System.out::println);
    }

    private void givenPeople() {
        givenPerson("martin", 10, "A", LocalDate.of(1991,8,15));
        givenPerson("david", 9, "B", LocalDate.of(1991,7,15));
        givenBlockPerson("jieun", 27, "AB");
        givenBlockPerson("martin", 11, "AB");
    }


    private void givenBlockPerson(String name, int age, String bloodType) {
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));
        personRepository.save(blockPerson);
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);
        personRepository.save(person);
    }

}