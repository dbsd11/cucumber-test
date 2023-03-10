package group.bison.cucumber.suite.source.news.click_link;

import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class SearchForInformation {

    @When("{actor} click link {string}")
    public void clickLink(Actor actor, String link) {
        actor.attemptsTo(Task.where("{0} click link '" + link + "'", Click.on((Target)null)));
    }

}
