package com.aegisep.hibernate.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id", nullable = false)
    private Long person_id;

    @Column(name = "firstname", length = 20)
    private String firstname;

    @Column(name = "lastname", length = 20)
    private String lastname;

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return person_id.equals(person.person_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id);
    }
}