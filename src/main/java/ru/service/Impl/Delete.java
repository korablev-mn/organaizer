package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.dao.PersonDAOImpl;
import ru.entity.Person;
import ru.service.Command;
import java.lang.reflect.Field;
import java.util.Scanner;

@Component
public class Delete extends Command {
    @Override
    public boolean execute() {
        Scanner s = new Scanner(System.in);
        boolean match = false;
        String parametrName = null;
        String parametr = null;

        System.out.println("Пример : delete id=2 или email='1@mail.ru'");
        String txt = s.nextLine();

        try {
            String[] msg = txt.split("=");
            parametrName = msg[0].trim();
            parametr = msg[1].trim();
        } catch (Exception e){
            System.out.println("Ошибка ввода !");
        }
        for(Field field: Person.class.getDeclaredFields()){
            if(field.getName().equals(parametrName)){
                match = true;
                if(deleting(parametrName, parametr)){
                    System.out.println("Удалено ");
                } else {
                    System.out.println("Ошибка удаления ");
                }
                break;
            }
        }
        if(!match){
            System.out.println("Команда не распознана, повторите ...");
        }
        System.out.println("-> ");
        return super.execute();
    }
    private boolean deleting(String name, String var){
        Person person = PersonDAOImpl.getInstance().find(name, var);
        if(person != null){
            return PersonDAOImpl.getInstance().delete(person);
        } else {
            return false;
        }
    }
}