package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.dao.PersonDAOImpl;
import ru.entity.Person;
import ru.entity.Persons;
import ru.service.Command;

@Component
public class List extends Command {
    @Override
    public boolean execute() {
        Persons persons = PersonDAOImpl.getInstance().getXML();
        for (Person person: persons.getPersons()){
            System.out.println(person.toString());
        }
        System.out.println("-> ");
        return super.execute();
    }
}