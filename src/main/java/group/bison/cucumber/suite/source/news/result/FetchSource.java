package group.bison.cucumber.suite.source.news.result;

import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class FetchSource {

    @Then("{actor} fetch source success")
    public void fetchSourceSuccess(Actor actor) {
        actor.attemptsTo(Ensure.that(Target.the("body").locatedBy("/html/body")).isDisplayed());
        
        actor.attemptsTo(Fetch, null);
    }
}
