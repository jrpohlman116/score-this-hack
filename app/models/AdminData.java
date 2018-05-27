package models;


import io.ebean.Finder;
import play.data.validation.Constraints;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminData extends Model{
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivilege(String access){
        this.privilege = access;
    }


    @Id @Constraints.Required
    public String username;
    @Constraints.Required
    public String password;

    public String privilege;


    public static Finder<String, AdminData> find = new Finder<>(AdminData.class);

}
