package group.bison.cucumber.suite.source.rss.visite_rss_feed;

import org.apache.commons.lang3.StringUtils;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import group.bison.cucumber.suite.source.rss.read_rss_url.ReadFromRssUrl;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;

public class VisiteRssFeed {

    @When("{actor} visite rss feed {string}")
    public void visiteRssFeed(Actor actor, String keyword) {   
        SyndFeed syndFeed = actor.recall(ReadFromRssUrl.KEY_SYND_FEED);

        SyndEntry matchEntry = syndFeed.getEntries().stream().filter(syndEntry -> StringUtils.contains(syndEntry.getTitle(), keyword) || StringUtils.contains(syndEntry.getComments(), keyword) || 
            StringUtils.contains(syndEntry.getAuthor(), keyword) || StringUtils.contains(syndEntry.getLink(), keyword)).findAny().orElse(null);
        
        actor.attemptsTo(Ensure.that(matchEntry != null).isTrue());
        actor.attemptsTo(Open.url(matchEntry.getLink()));
    }
}