package ru.service.Impl;

import org.springframework.stereotype.Component;
import ru.service.Command;

@Component
public class Exit extends Command {
    @Override
    public boolean execute() {
        System.out.println("-> exit");
        return false;
    }
}