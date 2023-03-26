package group.bison.cucumber.suite.source.maps.search_position;

import org.openqa.selenium.Keys;

import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class SearchPosition {

    public static String KEY_SEARCH_ADDRESS = "searchAddress";

    @When("{actor} search address {string} in {string}")
    public void searchAddress(Actor actor, String address, String selector) {
        actor.attemptsTo(Ensure.that(Target.the("input").locatedBy(selector)).isEnabled());
        actor.attemptsTo(Task.where("{0} search for '" + address + "'",
                Enter.theValue(address)
                        .into(Target.the("search field").locatedBy(selector))
                        .thenHit(Keys.ENTER)));
        actor.attemptsTo(Switch.toTheOtherWindow());
        actor.remember(KEY_SEARCH_ADDRESS, address);
    }
}
