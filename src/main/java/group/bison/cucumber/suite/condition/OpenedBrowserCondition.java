package group.bison.cucumber.suite.condition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

public class OpenedBrowserCondition {

    @Managed
    WebDriver isBrowser;
    
    @Given("{actor} opened browser")
    public void openBrowser(Actor actor) {
        actor.can(BrowseTheWeb.with(isBrowser));
    }
}
