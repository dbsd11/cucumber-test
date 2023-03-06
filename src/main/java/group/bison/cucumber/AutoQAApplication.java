package group.bison.cucumber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@MapperScan("group.bison.cucumber.dao")
public class AutoQAApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(AutoQAApplication.class, args);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

}