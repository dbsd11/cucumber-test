package group.bison.cucumber;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"group.bison.netty.modules", "group.bison.cucumber"}, exclude = {ErrorMvcAutoConfiguration.class}, excludeName = {"com.alipay.sofa.ark.springboot.ArkAutoConfiguration", "com.alipay.sofa.ark.springboot1.CompatibleSpringBoot1AutoConfiguration", "com.alipay.sofa.ark.springboot2.CompatibleSpringBoot2AutoConfiguration",
        "org.springframework.boot.actuate.autoconfigure.metrics.web.client.HttpClientMetricsAutoConfiguration", "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration", "org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration"
})
@EnableWebMvc
@EnableCaching
@MapperScan("group.bison.cucumber.rest.repository")
public class AutoQAApplication {

    public static void main(String[] args) throws Exception {
        Logger logger = null;
        try {
            logger = LoggerFactory.getLogger(AutoQAApplication.class);

            System.out.println("ApplicationContextInitializer: " + ApplicationContextInitializer.class.getClassLoader().getClass().getName());
            System.out.println("SofaRuntimeSpringContextInitializer: " + ClassUtils.forName("com.alipay.sofa.runtime.SofaRuntimeSpringContextInitializer", Thread.currentThread().getContextClassLoader()).getClassLoader().getClass().getName());
            System.setProperty("com.alipay.sofa.boot.jvmFilterEnable", "true");
            System.setProperty("com.alipay.sofa.boot.dynamicJvmServiceCacheEnable", "true");
            System.setProperty("com.alipay.sofa.boot.skipJvmSerialize", "true");
            SpringApplication application = new SpringApplication(AutoQAApplication.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.setApplicationContextFactory(ApplicationContextFactory.ofContextClass(GenericWebApplicationContext.class));
            application.run(args);
        } catch (Throwable e) {
            if(logger != null) {
                logger.error(e.getMessage(), e);
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

}