package group.bison.cucumber.test.demo;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = {"classpath:/group/bison/cucumber"},
        plugin = {"pretty"},
        features = "classpath:features/search"
)
public class CucumberSearchTestSuite {
        
}
