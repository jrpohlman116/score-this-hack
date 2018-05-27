package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;

@Entity
public class Users extends Model {
    public String username;

    public static Finder<String, Users> find = new Finder<>(Users.class);


}
