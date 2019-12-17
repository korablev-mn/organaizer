package ru.service;

import java.io.IOException;

public abstract class Command {
    private String name;

    public boolean execute() {
        return true;
    }

    public Command() {
        this.name = this.getClass().getSimpleName().toLowerCase();
    }

    public String getName() {
        return name;
    }
}