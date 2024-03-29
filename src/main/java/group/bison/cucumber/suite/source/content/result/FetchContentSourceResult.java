package group.bison.cucumber.suite.source.content.result;

import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class FetchContentSourceResult {

    @Then("{actor} fetch content text success")
    public void fetchSourceSuccess(Actor actor) {        
        Target bodyTarget = Target.the("body").locatedBy("/html/body");
        ListOfWebElementFacades webElementFacades = bodyTarget.resolveAllFor(actor);
        actor.attemptsTo(Ensure.that(webElementFacades != null && !webElementFacades.isEmpty()).isTrue());
        String title = BrowseTheWeb.as(actor).getTitle();
        String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        String content = BrowseTheWeb.as(actor).getDriver().getPageSource();
        log.info("fetch source success url {} title {} content {}", url, title, content);
    }
}