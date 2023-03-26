package group.bison.cucumber.suite.source.maps.result;

import org.openqa.selenium.OutputType;

import group.bison.cucumber.suite.source.maps.search_position.SearchPosition;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class SearchMapPositionResult {

    @Then("{actor} find address position")
    public void findAddressPosition(Actor actor) {
        String searchAddress = (String)actor.recall(SearchPosition.KEY_SEARCH_ADDRESS);

        Target bodyTarget = Target.the("body").locatedBy("/html/body");
        ListOfWebElementFacades webElementFacades = bodyTarget.resolveAllFor(actor);
        actor.attemptsTo(Ensure.that(webElementFacades != null && !webElementFacades.isEmpty()).isTrue());
        String bodyBase64 = webElementFacades.get(0).getElement().getScreenshotAs(OutputType.BASE64);
        log.info("search address {} success. {}", searchAddress, bodyBase64);
    }
}
