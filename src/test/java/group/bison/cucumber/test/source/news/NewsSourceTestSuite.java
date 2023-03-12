package group.bison.cucumber.test.source.news;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import group.bison.cucumber.suite.source.news.NewsSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class NewsSourceTestSuite {

    
    @Test
    public void test() throws Exception {
        new NewsSource().call();
    }
        
}
