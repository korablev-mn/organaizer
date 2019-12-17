package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.dao.PersonDAOImpl;
import ru.entity.Person;
import ru.service.Command;
import ru.service.PrimaryKey;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class Insert extends Command {

    @Override
    public boolean execute(){

        Person person = null;
        Scanner s = new Scanner(System.in);
        Map<String, String> data = new HashMap<String, String>();

        for (Field field : Person.class.getDeclaredFields()) {
            System.out.print(field.getName() + " : ");
            if(field.getName().equals("id")){
                String id = String.valueOf(PrimaryKey.getInstance().getId());
                System.out.print(id + "\n");
                data.put(field.getName(), id);
            } else{
                String text = s.nextLine();
                data.put(field.getName(), text);
            }
        }

        person = new Person(data);
        PersonDAOImpl.getInstance().save(person);
        System.out.println("-> ");
        return super.execute();
    }
}