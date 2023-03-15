package group.bison.cucumber.suite.source.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

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

public class CsdnContentSource extends ContentSource {
    
    public CsdnContentSource(SourceVO sourceVO) {
        super(sourceVO);
    }

    @Override
    public Object call() throws Exception {
        String website = "https://www.csdn.net/";

        String searchTerm = "k8s";

        String searchFormSelector = "#toolbar-search-input";

        EventBus eventBus = new TimeServiceEventBus(Clock.systemUTC(), UUID::randomUUID);

        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder().setWip(false).addPluginName("pretty").addGlue(new URI("classpath:/group/bison/cucumber")).build();
        
        io.cucumber.core.runtime.Runtime runtime = io.cucumber.core.runtime.Runtime.builder().withFeatureSupplier(() -> {
            List<Feature> featureList = new LinkedList<>();

            Resource resource = new Resource() {

                @Override
                public URI getUri() {
                    return URI.create("file:///tmp/search-contents");
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    String featureStr = "Feature: Search Contents\n" + //
                                                "\n" + //
                                                "  Scenario: Find Csdn Search Rank List By Keyword\n" + //
                                                "    Given Sergey opened browser\n" + //
                                                "    When he go to website \"${var}\"\n".replace("${var}", website) + //
                                                "    When he search for \"${var1}\" in \"${var2}\"\n".replace("${var1}", searchTerm).replace("${var2}", searchFormSelector) + //
                                                "    Then he find search rank list\n" + //
                                                 "\n" + //
                                                "  Scenario: Search Csdn Contents\n" + //
                                                "    Given Sergey have found search rank list\n" + //
                                                "    When he click link at 0\n" + //
                                                "    Then he fetch content text success\n" + //
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
