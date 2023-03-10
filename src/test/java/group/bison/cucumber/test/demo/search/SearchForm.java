package group.bison.cucumber.test.demo.search;

import net.serenitybdd.screenplay.targets.Target;

class SearchForm {
    static Target SEARCH_FIELD = Target.the("search field")
                                       .locatedBy("#searchInput");

}
