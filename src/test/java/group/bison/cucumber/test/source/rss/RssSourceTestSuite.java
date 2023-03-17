package group.bison.cucumber.test.source.rss;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import group.bison.cucumber.common.tools.PersistTool;
import group.bison.cucumber.suite.source.rss.RssSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class RssSourceTestSuite {
    
    @Test
    public void test() throws Exception {
        PersistTool persistTool = new PersistTool();
        persistTool.afterPropertiesSet();

        Field redisTemplateField = PersistTool.class.getDeclaredField("redisTemplate");
        redisTemplateField.setAccessible(true);

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration());
        connectionFactory.afterPropertiesSet();
        redisTemplateField.set(persistTool, new StringRedisTemplate(connectionFactory));

        new RssSource(null).call();
    }

}
