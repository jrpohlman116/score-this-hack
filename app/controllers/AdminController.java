package controllers;

import models.AdminData;
import models.AdminID;
import models.CompetitionResults;
import models.Users;
import org.apache.commons.codec.digest.DigestUtils;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.adminpage;
import views.html.error;
import views.html.login;
import views.html.registration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result loginPage(){
        Form<AdminData> loginForm = formFactory.form(AdminData.class);
        return  ok(login.render(loginForm,""));
    }

    public Result userlogin(){

        Form<AdminData> loginForm = formFactory.form(AdminData.class).bindFromRequest();
        AdminData loginCredentials = loginForm.get();

        AdminData login = AdminData.find.query().where().eq("username", loginCredentials.username).eq("password", DigestUtils.md5Hex(loginCredentials.password)).findUnique();

        if (login == null){
            return ok(error.render("User Not Found"));
        }
        else{
            if (login.privilege.matches("admin")){
                Form<Users> presentersForm = formFactory.form(Users.class);
                List<Users> presenters = Users.find.query().findList();
                List<CompetitionResults> results = CompetitionResults.find.query().findList();
                results = totaledResults(results);

                return ok(adminpage.render(login.username,presentersForm,presenters,results));
            }
            else{
                return ok(error.render("Not Authorized"));

            }
        }
    }

    public Result registrationPage(){
        Form<AdminID> registrationForm = formFactory.form(AdminID.class);
        return  ok(registration.render(registrationForm));
    }

    public Result save(){
        Form<AdminID> registrationForm = formFactory.form(AdminID.class).bindFromRequest();
        AdminID admin = registrationForm.get();
        System.out.println(admin.password);
        System.out.println(admin.confPassword + " CONFPASSWORD");

        if (admin.password.equals(admin.confPassword)){
            AdminData loginCredentials = new AdminData();
            loginCredentials.setUsername(admin.username);
            loginCredentials.setPrivilege("judge");
            loginCredentials.setPassword(DigestUtils.md5Hex(admin.password));
            loginCredentials.save();
        }
        else{
            return ok(error.render("Passwords do not match"));
        }

        Form<AdminData> loginForm = formFactory.form(AdminData.class);

        return ok(login.render(loginForm,"You have successfully signed up"));
    }

    public Result savePresenter(String admin){
        Form<Users> presentersForm = formFactory.form(Users.class).bindFromRequest();
        Users presenter = presentersForm.get();
        presenter.save();

        presentersForm = formFactory.form(Users.class);
        List<Users> presenters = Users.find.query().findList();
        List<CompetitionResults> results = CompetitionResults.find.query().findList();
        results = totaledResults(results);

        return ok(adminpage.render(admin,presentersForm,presenters,results));
    }

    public Result viewPresenter(String admin){
        Form<Users> presentersForm = formFactory.form(Users.class).bindFromRequest();
        Users presenter = presentersForm.get();

        return TODO;
    }

    public List<CompetitionResults> totaledResults(List<CompetitionResults> results){
        HashMap<String, CompetitionResults> totalResultsMap = new HashMap<>();
        List<CompetitionResults> totaledResultsList = new ArrayList<>();

        for(CompetitionResults result : results){
            if (totalResultsMap.containsKey(result.presenter)){
                CompetitionResults currentPresenterTotal = totalResultsMap.get(result.presenter);
                currentPresenterTotal.uiDesign += result.uiDesign;
                currentPresenterTotal.userExperience += result.userExperience;
                currentPresenterTotal.practicality += result.practicality;
                currentPresenterTotal.originality += result.originality;
                currentPresenterTotal.presentation += result.presentation;
                currentPresenterTotal.comments += "\n" + result.comments;
                totalResultsMap.put(result.presenter, currentPresenterTotal);
            }
            else{
                totalResultsMap.put(result.presenter, result);
            }
        }

        for (Map.Entry<String, CompetitionResults> entry : totalResultsMap.entrySet()){
            totaledResultsList.add(entry.getValue());
        }

        return totaledResultsList;
    }
}
