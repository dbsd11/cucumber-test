package group.bison.cucumber.rest.config;

import group.bison.cucumber.domain.source.service.SourceService;
import group.bison.cucumber.domain.source.service.impl.SourceServiceImpl;
import group.bison.cucumber.domain.target.service.TargetService;
import group.bison.cucumber.domain.target.service.impl.TargetServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAutowireConfig {

    @Bean
    public SourceService sourceService() {
        return new SourceServiceImpl();
    }

    @Bean
    public TargetService targetService() {
        return new TargetServiceImpl();
    }
}
