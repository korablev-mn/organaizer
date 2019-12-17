package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.dao.PersonDAOImpl;
import ru.entity.Person;
import ru.service.Command;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class Update extends Command {
    @Override
    public boolean execute() {
        Scanner s = new Scanner(System.in);
        Map<String, String> data = new HashMap<String, String>();
        String id;
        Person person = null;

        System.out.print("where id = ");
        id = s.nextLine().trim();

        person = PersonDAOImpl.getInstance().find("id", id);
        if (person != null) {
            Class<? extends Object> clazz = person.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object value = field.get(person);
                    System.out.print(field.getName() + " = " + value + " new:  ");
                    data.put(field.getName(), s.nextLine().trim());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            data.put("id", id);
            Person newPerson = new Person(data);
            newPerson.setId(Long.valueOf(id));
            PersonDAOImpl.getInstance().delete(person);
            PersonDAOImpl.getInstance().update(newPerson);
        }

        System.out.println("-> ");
        return super.execute();
    }
}