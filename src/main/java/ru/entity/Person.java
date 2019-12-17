package ru.entity;

import javax.xml.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@XmlRootElement
@XmlType(propOrder = {"id", "firstName", "secondName", "lastName", "profession", "nameOfOrganization", "email", "phones"})
public class Person {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String profession;
    private String nameOfOrganization;
    private String email;
    private List<Phone> phones;

    public Person() {
        phones = new ArrayList<Phone>();
    }

    public Person(Map<String, String> map) {
        this.phones = new ArrayList<Phone>();
        try {
            this.id = Long.valueOf(map.get("id"));
            this.firstName = map.get("firstName");
            this.secondName = map.get("secondName");
            this.lastName = map.get("lastName");
            this.profession = map.get("profession");
            this.nameOfOrganization = map.get("nameOfOrganization");
            this.email = map.get("email");
            String[] phone = map.get("phones").split("[^0-9]+");
            for (int i = 0; i < phone.length; i++) {
                this.phones.add(new Phone(phone[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @XmlElement
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    @XmlElement
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    @XmlElement
    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    @XmlElementWrapper(name = "phones")
    @XmlElements({@XmlElement(name = "phone", type = Phone.class)})
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profession='" + profession + '\'' +
                ", nameOfOrganization='" + nameOfOrganization + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }

    public String getPhonesString() {
        return phones.stream().map(x -> String.valueOf(x.getPhoneNumber())).collect(Collectors.joining(", ", "", ""));
    }

    public void get(Field field) {

    }
}