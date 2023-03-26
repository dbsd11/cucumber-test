package group.bison.cucumber.test.storage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import alluxio.AlluxioURI;
import alluxio.client.file.FileOutStream;
import alluxio.client.file.FileSystem;
import alluxio.conf.Configuration;
import alluxio.conf.PropertyKey;
import alluxio.grpc.CreateFilePOptions;
import group.bison.cucumber.common.tools.PersistTool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class StorageTestSuite {

    public static PersistTool initPersistTool() {
        if (PersistTool.getInstance() != null) {
            return PersistTool.getInstance();
        }

        PersistTool persistTool = null;
        try {
            persistTool = new PersistTool();
            persistTool.afterPropertiesSet();

            Field redisTemplateField = PersistTool.class.getDeclaredField("redisTemplate");
            redisTemplateField.setAccessible(true);

            LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                    new RedisStandaloneConfiguration());
            connectionFactory.afterPropertiesSet();
            redisTemplateField.set(persistTool, new StringRedisTemplate(connectionFactory));
            log.error("initPersistTool success");
        } catch (Exception e) {
            log.error("initPersistTool failed", e);
        }
        return persistTool;
    }

    // @Test
    public void test() throws Exception {
        Configuration.set(PropertyKey.MASTER_HOSTNAME, "alluxio-worker");
        Configuration.set(PropertyKey.SECURITY_LOGIN_USERNAME, "alluxio");
        FileSystem fs = FileSystem.Factory.get();
        AlluxioURI path = new AlluxioURI("/test-dir/test_01");
        // Create a file and get its output stream
        FileOutStream out = fs.createFile(path,
                CreateFilePOptions.newBuilder().setOverwrite(true).setRecursive(true).build());
        // Write data
        out.write(("test storage string " + String.valueOf(System.currentTimeMillis()) + "\n").getBytes());
        // Close and complete file
        out.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(fs.openFile(path)));
        String line = null;
        while ((line = br.readLine()) != null) {
            log.info("read line {}", line);
        }
    }
}
