package group.bison.cucumber.suite.source.rss.read_rss_url;

import java.net.URL;

import com.auth0.jwt.internal.org.apache.commons.codec.digest.DigestUtils;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import group.bison.cucumber.common.tools.PersistTool;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

@Slf4j
public class ReadFromRssUrl {

    public static String KEY_RSS_URL = "rssUrl";

    public static String KEY_SYND_FEED = "syndFeed";
    
    @When("{actor} read from rss url {string}")
    public void readFromRssUrl(Actor actor, String rssUrl) {
        SyndFeed feed = null;
        try {
            XmlReader reader = new XmlReader(new URL(rssUrl));
            log.info("Rss源的编码格式为：" + reader.getEncoding());
            SyndFeedInput input = new SyndFeedInput();
            // 得到SyndFeed对象，即得到Rss源里的所有信息     
            feed = input.build(reader);
        } catch (Exception e) {
            log.error("readFromRssUrl failed", e);
        }

        actor.remember(KEY_RSS_URL, rssUrl);
        actor.remember(KEY_SYND_FEED, feed);
    }

    @Then("{actor} get rss feed")
    public void getRssFeed(Actor actor) {
        SyndFeed feed = actor.recall(KEY_SYND_FEED);
        actor.attemptsTo(Ensure.that(feed != null).isTrue());

        String rssUrl = (String)actor.recall(KEY_RSS_URL);
        PersistTool.setObj(String.join("", "synd_rss_feed#", actor.getName(), "_", DigestUtils.md5Hex(rssUrl)), feed);
    }

    @Given("{actor} have readed from rss url {string}")
    public void haveReadFromRssUrl(Actor actor, String rssUrl) {
        SyndFeed syndFeed = (SyndFeed)PersistTool.getObj(String.join("", "synd_rss_feed#", actor.getName(), "_", DigestUtils.md5Hex(rssUrl)), null);
        actor.attemptsTo(Ensure.that(syndFeed != null).isTrue());
        
        actor.remember(KEY_SYND_FEED, syndFeed);
    }
}
