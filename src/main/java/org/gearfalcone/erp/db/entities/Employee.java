package org.gearfalcone.erp.db.entities;

import org.gearfalcone.erp.db.annotations.MongoCollectionName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/**
 * Created by andy on 13.04.15.
 */

@MongoCollectionName(name="employees")
public class Employee {

    private BigInteger id;

    @NotNull
    @Size(min=1, max = 100)
    private String firstname;

    @NotNull
    @Size(min=1, max = 100)
    private String lastname;

    @Size(max=100)
    private String patronymic;

    @NotNull
    @Size(min=3, max=100)
    private String login;

    @NotNull
    @Size(min=3, max=100)
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
