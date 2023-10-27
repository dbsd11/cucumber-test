package group.bison.cucumber.config;

import group.bison.netty.modules.springcontext.RootSpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.HandlerMapping;

@Slf4j
@Configuration
public class GlobalWebConfig implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(RootSpringContextHolder.get()  == null || RootSpringContextHolder.get() == applicationContext) {
            return;
        }

        try {
            ApplicationContext rootApplicationContext = RootSpringContextHolder.get();
            DefaultListableBeanFactory rootBeanFactory = (DefaultListableBeanFactory)rootApplicationContext.getAutowireCapableBeanFactory();

            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            String[] handlerMappingBeanNames = defaultListableBeanFactory.getBeanNamesForType(HandlerMapping.class);

            for(String handlerMappingBeanName : handlerMappingBeanNames) {
                HandlerMapping handlerMapping = (HandlerMapping)defaultListableBeanFactory.getBean(handlerMappingBeanName);
                String register2RootHandlerMappingBeanName = String.join("", "cucumber_", handlerMappingBeanName);
                if(rootBeanFactory.containsBean(register2RootHandlerMappingBeanName)) {
                    rootBeanFactory.destroySingleton(register2RootHandlerMappingBeanName);
                }
                rootBeanFactory.registerSingleton(register2RootHandlerMappingBeanName, handlerMapping);
            }

            ApplicationEventMulticaster applicationEventMulticaster = rootBeanFactory.getBean(ApplicationEventMulticaster.class);
            ContextRefreshedEvent contextRefreshedEvent = new ContextRefreshedEvent(rootApplicationContext);
            applicationEventMulticaster.multicastEvent(contextRefreshedEvent);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }
}