package ru.stqa.pft.m2.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table (name="customer_entity")
public class CustomerData {

    @Id
    @GeneratedValue
    @Column (name="entity_id")
    private int id;
    private String firstname;
    private String lastname;
    private String email;
  /*  @Column (name="gender")
    @Type(type="smallint")
    private String gender; */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerData that = (CustomerData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "email='" + email + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

  /*  public String getGender() {
        return gender;
    }*/

    public CustomerData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CustomerData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CustomerData withEmail(String email) {
        this.email = email;
        return this;
    }

   /* public CustomerData withGender(String gender) {
        this.gender = gender;
        return this;
    }*/

    public int getId() {
        return id;
    }

    public CustomerData withId(int id) {
        this.id = id;
        return this;
    }
}
