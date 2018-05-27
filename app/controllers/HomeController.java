package controllers;

import models.AdminData;
import models.AdminID;
import models.CompetitionResults;
import models.Users;
import org.apache.commons.codec.digest.DigestUtils;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    @Inject
    FormFactory formFactory;
    public Result index() {
        Form<Users> userForm = formFactory.form(Users.class);

        return ok(index.render("Your new application is ready.", userForm));
    }

    public Result startVoting(){
        Form<Users> userForm = formFactory.form(Users.class).bindFromRequest();
        Users judge = userForm.get();

        List<Users> presenters = Users.find.query().findList();
        Form<CompetitionResults> competitionForm = formFactory.form(CompetitionResults.class);

        return ok(voterpage.render(judge.username, "", presenters, competitionForm));

    }

    public Result score(String judgeName){
        Form<CompetitionResults> competitionForm = formFactory.form(CompetitionResults.class).bindFromRequest();
        CompetitionResults results = competitionForm.get();
        results.save();

        List<Users> presenters = Users.find.query().findList();
        competitionForm = formFactory.form(CompetitionResults.class);

        return ok(voterpage.render(judgeName, "Thank you for your submission.", presenters, competitionForm));

    }




}
