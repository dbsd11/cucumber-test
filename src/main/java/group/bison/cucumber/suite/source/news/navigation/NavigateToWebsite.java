package group.bison.cucumber.suite.source.news.navigation;

import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class NavigateToWebsite {

    @When("{actor} go to website {string}")
    public void go2Website(Actor actor, String website) {
        actor.attemptsTo(Task.where("{0} go to website '" + website + "'",
                (tUser) -> tUser.abilityTo(BrowseTheWeb.class).openUrl(website)));
        actor.attemptsTo(Ensure.that(Target.the("a").locatedBy("//a")).isEnabled());
        // org.apache.hc.client5.http.impl.classic.InternalHttpClient
    }

}
