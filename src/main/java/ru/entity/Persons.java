package ru.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "persons")
public class Persons {

    private Long id;
    private ArrayList<Person> persons;

    public Persons() {
        persons = new ArrayList<Person>();
    }

    public void add(Person person) {
        persons.add(person);
    }

    public void remove(Person person) {
        persons.remove(person);
    }

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
}