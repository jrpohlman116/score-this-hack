package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;

@Entity
public class CompetitionResults extends Model {
    public String presenter;
    public Integer uiDesign;
    public Integer userExperience;
    public Integer practicality;
    public Integer originality;
    public Integer presentation;
    public String comments;

    public static Finder<String, CompetitionResults> find = new Finder<>(CompetitionResults.class);

}
