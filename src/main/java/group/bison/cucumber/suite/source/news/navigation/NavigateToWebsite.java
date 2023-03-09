package group.bison.cucumber.suite.source.news.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.DefaultUrl;

public class NavigateToWebsite {

    public static Performable theWikipediaHomePage() {
        return Task.where("{0} opens the Wikipedia home page",
                Open.browserOn().the(WikipediaHomePage.class));
    }

    @DefaultUrl("https://wikipedia.org")
    public static class WikipediaHomePage extends PageObject {}
}
