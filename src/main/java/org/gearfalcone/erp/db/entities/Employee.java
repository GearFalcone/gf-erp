package org.gearfalcone.erp.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.gearfalcone.erp.db.mongo.json.MongoObjectIdDeserializer;
import org.gearfalcone.erp.db.mongo.json.MongoObjectIdSerializer;
import org.gearfalcone.erp.db.validation.annotations.UniqueLogin;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by andy on 13.04.15.
 */

@Document(collection="employees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @Id
    @JsonSerialize(using = MongoObjectIdSerializer.class)
    @JsonDeserialize(using = MongoObjectIdDeserializer.class)
    private String _id;

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
    @UniqueLogin
    private String login;

    @NotNull
    @Size(min=3, max=100)
    private String password;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
