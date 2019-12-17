package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.dao.PersonDAOImpl;
import ru.entity.Person;
import ru.service.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Find extends Command {
    @Override
    public boolean execute() {
        List<Person> persons;
        System.out.println("Поиск по номеру телефона :");
        Scanner s = new Scanner(System.in);
        persons = PersonDAOImpl.getInstance().findByNumberPhone(s.nextLine().trim());
        for(Person person: persons){
            System.out.println("-> " + person);
        }
        System.out.println("-> ");
        return super.execute();
    }
}
