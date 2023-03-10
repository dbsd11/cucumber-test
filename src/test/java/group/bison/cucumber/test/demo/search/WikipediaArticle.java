package group.bison.cucumber.test.demo.search;

import net.serenitybdd.screenplay.targets.Target;

public class WikipediaArticle {

    public static final Target HEADING =  Target.the("article heading").locatedBy("#firstHeading");
}