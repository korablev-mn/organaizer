package ru.dao;

import ru.entity.Person;
import ru.entity.Persons;

import javax.xml.bind.JAXBException;
import java.util.List;

interface PersonDAO {
    Persons getXML();
    void save(Person person);
    void update(Person person);
    boolean delete(Person person);
    void chekFile() throws JAXBException;
    void marshalling(Persons persons);
    Person find(String parametr, String arg);
    List<Person> findByNumberPhone(String number);
}