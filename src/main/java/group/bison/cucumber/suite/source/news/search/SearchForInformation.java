package group.bison.cucumber.suite.source.news.search;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;

import group.bison.cucumber.common.tools.PersistTool;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.PerformOn;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class SearchForInformation {

    public static String KEY_SEARCH_TERM = "searchTerm";
    public static String KEY_SEARCH_RANK_LIST = "searchRankList";

    @When("{actor} search for {string} in {string}")
    public void go2Website(Actor actor, String terms, String selector) {
        actor.attemptsTo(Ensure.that(Target.the("input").locatedBy(selector)).isEnabled());
        actor.attemptsTo(Task.where("{0} search for '" + terms + "'",
                Enter.theValue(terms)
                        .into(Target.the("search field").locatedBy(selector))
                        .thenHit(Keys.ENTER)));
        actor.remember(KEY_SEARCH_TERM, terms);
    }

    @When("{actor} switch to other window")
    public void go2Website(Actor actor) {
        actor.attemptsTo(Switch.toTheOtherWindow());
    }

    @Then("{actor} find search rank list")
    public void findSearchRankList(Actor actor) {
        String searchTerm = actor.recall(SearchForInformation.KEY_SEARCH_TERM);
        actor.attemptsTo(Ensure.that(BrowseTheWeb.as(actor).waitForAnyTextToAppear(searchTerm).containsAllText(searchTerm)).isTrue());

        Target aTarget = Target.the("a").locatedBy("//a");
        actor.attemptsTo(Ensure.that(aTarget).isEnabled());
        
        List<String> aLinkList = new LinkedList<>();
        actor.attemptsTo(PerformOn.eachMatching(aTarget, (webElement -> {
            try {
                String aText = webElement.getTextContent();
                boolean isMatchElement = StringUtils.contains(aText, searchTerm);
                if(isMatchElement) { 
                    log.info("find match element a. text {} href {} ", aText, webElement.getAttribute("href"));
                    aLinkList.add(webElement.getAttribute("href"));
                }
            } catch (Exception e) {
                log.warn("skip process fail <a/> ", e.getMessage());
            }
        })));
        
        // storage
        PersistTool.setObj(String.join("", "search_ranke_list#", actor.getName()), aLinkList);
    }

    @Given("{actor} have found search rank list")
    public void haveFoundSearchRankList(Actor actor) {
        List<String> aLinkList = (List<String>)PersistTool.getObj(String.join("", "search_ranke_list#", actor.getName()), null);
        actor.attemptsTo(Ensure.that(CollectionUtils.isNotEmpty(aLinkList)).isTrue());
        actor.remember(KEY_SEARCH_RANK_LIST, aLinkList);
    }
}
