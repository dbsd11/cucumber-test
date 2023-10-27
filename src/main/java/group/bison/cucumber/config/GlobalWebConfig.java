package group.bison.cucumber.config;

import group.bison.netty.modules.springcontext.RootSpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@Slf4j
@Configuration
public class GlobalWebConfig implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Autowired(required = false)
    private List<HandlerMapping> handlerMappingList;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(RootSpringContextHolder.get()  == null || RootSpringContextHolder.get() == applicationContext) {
            return;
        }

        if(CollectionUtils.isEmpty(handlerMappingList)) {
            return;
        }

        try {
            ApplicationContext rootApplicationContext = RootSpringContextHolder.get();
            DefaultListableBeanFactory rootBeanFactory = (DefaultListableBeanFactory)rootApplicationContext.getAutowireCapableBeanFactory();

            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            String[] handlerMappingBeanNames = defaultListableBeanFactory.getBeanNamesForType(HandlerMapping.class);

            for(String handlerMappingBeanName : handlerMappingBeanNames) {
                Object handlerMapping = defaultListableBeanFactory.getBean(handlerMappingBeanName);
                if(!(handlerMapping instanceof HandlerMapping)) {
                    break;
                }
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