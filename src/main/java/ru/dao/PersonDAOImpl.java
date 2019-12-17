package ru.dao;

import ru.entity.Person;
import ru.entity.Persons;
import ru.entity.Phone;
import ru.service.PrimaryKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDAOImpl implements PersonDAO {

    private static JAXBContext jaxbContext;
    private static PersonDAOImpl instance;
    private static Marshaller marshaller;
    private static Unmarshaller unmarshaller;
    private static File file;
    private static Persons persons;

    private PersonDAOImpl() {
    }

    public static PersonDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new PersonDAOImpl();
                jaxbContext = JAXBContext.newInstance(Persons.class);
                marshaller = jaxbContext.createMarshaller();
                unmarshaller = jaxbContext.createUnmarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                initFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static void initFile() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/app.properties");
            property.load(fis);
            String fileName = property.getProperty("app.file");
            file = new File(fileName);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public void chekFile() throws JAXBException {
        try {
            persons = (Persons) unmarshaller.unmarshal(file);
            PrimaryKey.getInstance().setId(persons.getId());
        } catch (JAXBException e) {
            Persons personsNew = new Persons();
            personsNew.setId(PrimaryKey.getInstance().getId());
            marshaller.marshal(personsNew, file);
        }
    }

    public void marshalling(Persons persons) {
        try {
            marshaller.marshal(persons, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Person find(String parametr, String arg) {
        getXML();

        for (Person person : persons.getPersons()) {
            if (person.toString().contains(parametr + "=" + arg)) {
                return person;
            }
        }
        return null;
    }

    public Persons getXML() {
        try {
            persons = (Persons) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void save(Person person) {
        PrimaryKey.getInstance().Increment();
        persons = getXML();
        persons.add(person);
        persons.setId(PrimaryKey.getInstance().getId());
        marshalling(persons);
    }

    public void update(Person person) {
        persons = getXML();
        persons.add(person);
        marshalling(persons);
    }

    public boolean delete(Person person) {
        try {
            persons.remove(person);
            marshalling(persons);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Person> findByNumberPhone(String number) {
        getXML();
        ArrayList<Person> result = new ArrayList<>();

        for (Person person : persons.getPersons()) {
            for (Phone phone: person.getPhones()){
                if(phone.getPhoneNumber().contains(number)){
                    result.add(person);
                }
            }
        }
        return result;
    }
}