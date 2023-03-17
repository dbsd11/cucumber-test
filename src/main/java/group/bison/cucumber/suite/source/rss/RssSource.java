package group.bison.cucumber.suite.source.rss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import group.bison.cucumber.domain.model.vo.SourceVO;
import io.cucumber.core.eventbus.EventBus;
import io.cucumber.core.feature.FeatureParser;
import io.cucumber.core.gherkin.Feature;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.core.options.RuntimeOptionsBuilder;
import io.cucumber.core.plugin.SerenityReporter;
import io.cucumber.core.resource.Resource;
import io.cucumber.core.runtime.TimeServiceEventBus;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.webdriver.Configuration;

public class RssSource implements Callable {
    
    private SourceVO sourceVO;

    public RssSource(SourceVO sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        String rssUrl = "https://api.feeddd.org/feeds/6131e13e1269c358aa0e1211";

        String searchTerm = "ChatGPT";

        EventBus eventBus = new TimeServiceEventBus(Clock.systemUTC(), UUID::randomUUID);

        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder().setWip(false).addPluginName("pretty").addGlue(new URI("classpath:/group/bison/cucumber")).build();
        
        io.cucumber.core.runtime.Runtime runtime = io.cucumber.core.runtime.Runtime.builder().withFeatureSupplier(() -> {
            List<Feature> featureList = new LinkedList<>();

            Resource resource = new Resource() {

                @Override
                public URI getUri() {
                    return URI.create("file:///tmp/rss_feed");
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    String featureStr = "Feature: Rss Feed\n" + //
                                                "\n" + //
                                                "  Scenario: Get 爱范儿 Rss Feed By Keyword\n" + //
                                                "    Given Sergey opened browser\n" + //
                                                "    When he read from rss url \"${var}\"\n".replace("${var}", rssUrl) + //
                                                "    Then he get rss feed\n" + //
                                                 "\n" + //
                                                "  Scenario: Visite 爱范儿 Rss Feed\n" + //
                                                "    Given Sergey have readed from rss url \"${var}\"\n".replace("${var}", rssUrl) + //
                                                "    When he visite rss feed \"${var}\"\n".replace("${var}", searchTerm) + //
                                                "    Then he fetch source success\n" + //
                                                "";
                    return new ByteArrayInputStream(featureStr.getBytes());
                }
                
            };
            Feature feature = new FeatureParser(() -> UUID.randomUUID()).parseResource(resource).orElse(null);
            if(feature != null) {
                featureList.add(feature);
            }
            return featureList;
        }).withEventBus(eventBus).withRuntimeOptions(runtimeOptions).withAdditionalPlugins(new SerenityReporter(Injectors.getInjector().getInstance(Configuration.class))).build();
        
        runtime.run();
        return null;
    }
}
