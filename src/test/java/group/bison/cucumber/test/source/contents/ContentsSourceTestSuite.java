package group.bison.cucumber.test.source.contents;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import group.bison.cucumber.suite.source.content.CsdnContentSource;
import group.bison.cucumber.test.storage.StorageTestSuite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class ContentsSourceTestSuite {
    
    @Before
    public void init() {
        StorageTestSuite.initPersistTool();   
    }

    @Test
    public void test() throws Exception {
        new CsdnContentSource(null).call();
    }
}
