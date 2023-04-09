package group.bison.cucumber;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableWebFlux
@EnableCaching
@MapperScan("group.bison.cucumber.rest.dao")
public class AutoQAApplication {

    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(AutoQAApplication.class, args);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

}