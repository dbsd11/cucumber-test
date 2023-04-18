package group.bison.cucumber.suite.source.recommend.list_goods;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.OutputType;

import group.bison.cucumber.common.tools.PersistTool;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.PerformOn;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

@Slf4j
public class SearchForGoods {

    public static String KEY_SEARCH_TERM = "searchTerm";
    public static String KEY_SEARCH_RANK_LIST = "searchGoodsList";

    @Then("{actor} find jingdong goods list")
    public void findJingdongGoodsList(Actor actor) {
        String searchTerm = actor.recall(SearchForGoods.KEY_SEARCH_TERM);
        actor.attemptsTo(Ensure.that(BrowseTheWeb.as(actor).waitForAnyTextToAppear(searchTerm).containsAllText(searchTerm)).isTrue());

        Target goodsLiTarget = Target.the("J_goodsList").locatedBy("//div[@id=\"J_goodsList\"]/ul/li[*]");
        actor.attemptsTo(Ensure.that(goodsLiTarget).isEnabled());
        
        List<Goods> goofs = new LinkedList<>();
        actor.attemptsTo(PerformOn.eachMatching(goodsLiTarget, (webElement -> {
            try {
                String shopUrl = webElement.findBy("//div[@class=\"p-img\"]/a").getAttribute("href");
                String imgUrl = webElement.findBy("//div[@class=\"p-img\"]/a/img").getAttribute("src");
                String title = webElement.findBy("//div[@class=\"p-name\"]").getTextContent();
                String price = webElement.findBy("//div[@class=\"p-price\"]").getTextContent();
                String snapshot = webElement.getScreenshotAs(OutputType.BASE64);
                Goods goods = new Goods();
                goods.setShopUrl(shopUrl);
                goods.setImgUrl(imgUrl);
                goods.setTitle(title);
                goods.setPrice(price);
                goods.setSnapshot(snapshot);

                log.info("find goods title {} price {}", title, price);
            } catch (Exception e) {
                log.warn("skip process fail <li/> ", e.getMessage());
            }
        })));
        
        // storage
        PersistTool.setObj(String.join("", "search_goods_list#", actor.getName()), goofs);
    }
}
