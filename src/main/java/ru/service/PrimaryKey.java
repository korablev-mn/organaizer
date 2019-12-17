package ru.service;

public class PrimaryKey {

    private static Long id;
    private static PrimaryKey instance;

    private PrimaryKey() {
    }

    public static PrimaryKey getInstance() {
        if (instance == null) {
            instance = new PrimaryKey();
            id = 1L;
        }
        return instance;
    }

    public static void setId(Long id) {
        PrimaryKey.id = id;
    }

    public static Long getId() {
        return id;
    }

    public void Increment() {
        id++;
    }
}