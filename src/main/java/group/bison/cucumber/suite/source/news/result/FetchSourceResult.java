package group.bison.cucumber.suite.source.news.result;

import org.openqa.selenium.OutputType;

import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class FetchSourceResult {

    @Then("{actor} fetch source success")
    public void fetchSourceSuccess(Actor actor) {        
        Target bodyTarget = Target.the("body").locatedBy("/html/body");
        ListOfWebElementFacades webElementFacades = bodyTarget.resolveAllFor(actor);
        actor.attemptsTo(Ensure.that(webElementFacades != null && !webElementFacades.isEmpty()).isTrue());
        String bodyBase64 = webElementFacades.get(0).getElement().getScreenshotAs(OutputType.BASE64);
        String title = BrowseTheWeb.as(actor).getTitle();
        String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        log.info("fetch source success url {} title {} body {}", url, title, bodyBase64);
    }
}