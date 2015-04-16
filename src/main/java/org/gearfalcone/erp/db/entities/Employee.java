package org.gearfalcone.erp.db.entities;

import org.gearfalcone.erp.db.annotations.MongoCollectionName;

import java.math.BigInteger;

/**
 * Created by andy on 13.04.15.
 */

@MongoCollectionName(name="employees")
public class Employee {

    private BigInteger id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String login;
    private String password;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
