package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.config.SpringComponentScan;
import ru.service.Command;

@Component
public class Help extends Command {
    @Override
    public boolean execute() {
        for (Command command: SpringComponentScan.getCommands()){
            System.out.println("-> "+command.getName());
        }
        System.out.println("-> ");
        return super.execute();
    }
}