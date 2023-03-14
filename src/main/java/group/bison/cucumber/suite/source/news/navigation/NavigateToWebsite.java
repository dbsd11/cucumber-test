package group.bison.cucumber.suite.source.news.navigation;

import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class NavigateToWebsite {

    @When("{actor} go to website {string}")
    public void go2Website(Actor actor, String website) {
        actor.attemptsTo(Task.where("{0} go to website '" + website + "'",
                (tUser) -> tUser.abilityTo(BrowseTheWeb.class).openUrl(website)));
    }

}
