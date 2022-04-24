package com.fastcampus.javaallinone.project.mycontact.repository;

import com.fastcampus.javaallinone.project.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
