package com.fastcampus.javaallinone.project.mycontact.service;

import com.fastcampus.javaallinone.project.mycontact.domain.Block;
import com.fastcampus.javaallinone.project.mycontact.domain.Person;
import com.fastcampus.javaallinone.project.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();

        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }
}
