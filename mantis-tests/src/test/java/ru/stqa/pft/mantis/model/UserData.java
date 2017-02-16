package ru.stqa.pft.mantis.model;


import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Expose
    @Column(name = "realname")
    private String realname;

    @Expose
    @Column(name = "username")
    private String username;

    @Expose
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "id")
    private int id;

    public String getRealname() {
        return realname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserData withRealname(String realname) {
        this.realname = realname;
        return this;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (realname != null ? !realname.equals(userData.realname) : userData.realname != null) return false;
        if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
        return email != null ? email.equals(userData.email) : userData.email == null;
    }

    @Override
    public int hashCode() {
        int result = realname != null ? realname.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
