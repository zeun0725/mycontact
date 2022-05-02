package com.fastcampus.javaallinone.project.mycontact.service;

import com.fastcampus.javaallinone.project.mycontact.domain.Block;
import com.fastcampus.javaallinone.project.mycontact.domain.Person;
import com.fastcampus.javaallinone.project.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public List<Person> getPeopleExcludeBlocks() {
        return personRepository.findByBlockIsNull();
    }

    @Transactional(readOnly = true)
    public Person getPerson(long id) {
        Person person = personRepository.findById(id).get();

        log.info("person: {}", person);
        return person;
    }

    public List<Person> getPeopleByName(String name) {
        return personRepository.findByName(name);
    }
}
