package group.bison.cucumber.test.source.rss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Import;

import group.bison.cucumber.suite.source.rss.RssSource;
import group.bison.cucumber.test.storage.StorageTestSuite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
@Import(StorageTestSuite.class)
public class RssSourceTestSuite {
    
    @Test
    public void test() throws Exception {
        new RssSource(null).call();
    }

}
