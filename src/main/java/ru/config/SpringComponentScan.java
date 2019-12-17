package ru.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import ru.dao.PersonDAOImpl;
import ru.service.Command;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SpringComponentScan {

    private static SpringComponentScan instance;
    private static ApplicationContext applicationContext;
    private static Set<Command> commands = new HashSet<Command>();

    public static SpringComponentScan getInstance() {
        if (instance == null) {
            instance = new SpringComponentScan();
            init();
        }
        return instance;
    }

    public static Set<Command> getCommands() {
        return commands;
    }

    public Set<Command> getAllCommands() {
        applicationContext = new AnnotationConfigApplicationContext("ru.service.Impl");
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            try {
                Command bean = (Command) applicationContext.getBean(beanName);
                commands.add(bean);
            } catch (ClassCastException e) {
            }
        }
        return commands;
    }

    public static void init() {
        try {
            PersonDAOImpl.getInstance().chekFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}