package group.bison.cucumber.suite.source.news.search;

import org.openqa.selenium.Keys;

import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class SearchForInformation {

    @When("{actor} search for {string} in {string}")
    public void go2Website(Actor actor, String terms, String selector) {
        actor.attemptsTo(Task.where("{0} search for '" + terms + "'",
                Enter.theValue(terms)
                        .into(Target.the("search field").locatedBy(selector))
                        .thenHit(Keys.ENTER)));
    }

}
