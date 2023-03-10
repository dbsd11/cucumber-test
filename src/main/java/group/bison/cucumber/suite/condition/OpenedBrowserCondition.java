package group.bison.cucumber.suite.condition;

import group.bison.cucumber.suite.parameter.BrowerDefaultHomePage;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenedBrowserCondition {
    
    @Given("{actor} opened browser")
    public void openBrowser(Actor actor) {
        actor.wasAbleTo(Task.where("{0} opens the browser",  Open.browserOn().the(BrowerDefaultHomePage.class)));;
    }
}
