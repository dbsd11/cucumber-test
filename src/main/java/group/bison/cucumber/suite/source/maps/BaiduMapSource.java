package group.bison.cucumber.suite.source.maps;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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

public class BaiduMapSource extends MapSource {
    
    public BaiduMapSource(SourceVO sourceVO) {
        super(sourceVO);
    }

    @Override
    public Object call() throws Exception {
        String website = "https://map.baidu.com/@12956692,4848305,13z";

        String searchAddress = "轰趴馆";

        String searchFormSelector = "#sole-input";

        EventBus eventBus = new TimeServiceEventBus(Clock.systemUTC(), UUID::randomUUID);

        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder().setWip(false).addPluginName("pretty").addGlue(new URI("classpath:/group/bison/cucumber")).build();
        
        io.cucumber.core.runtime.Runtime runtime = io.cucumber.core.runtime.Runtime.builder().withFeatureSupplier(() -> {
            List<Feature> featureList = new LinkedList<>();

            Resource resource = new Resource() {

                @Override
                public URI getUri() {
                    return URI.create("file:///tmp/search-maps");
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    String featureStr = "Feature: Search Maps\n" + //
                                                "\n" + //
                                                "  Scenario: Find Baidu Map By Address\n" + //
                                                "    Given Sergey opened browser\n" + //
                                                "    When he go to website \"${var}\"\n".replace("${var}", website) + //
                                                "    When he search address \"${var1}\" in \"${var2}\"\n".replace("${var1}", searchAddress).replace("${var2}", searchFormSelector) + //
                                                "    Then he find address position\n";
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
