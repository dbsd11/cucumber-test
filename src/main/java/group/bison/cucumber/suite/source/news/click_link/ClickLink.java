package group.bison.cucumber.suite.source.news.click_link;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import group.bison.cucumber.suite.source.news.search.SearchForInformation;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ClickLink {

    @When("{actor} click link at {int}")
    public void clickLink(Actor actor, int index) {
        List<String> aLinkList = (List<String>)actor.recall(SearchForInformation.KEY_SEARCH_RANK_LIST);
        actor.attemptsTo(Ensure.that(index >=0 && CollectionUtils.size(aLinkList) > index).isTrue());
        actor.attemptsTo(Task.where("{0} click link at " + index + "", Open.url(aLinkList.get(index))));
    }
}
