package group.bison.cucumber.suite.source.recommend;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.source.entity.SourceEntity;

public abstract class RecommendSource implements Callable {
    
    private SourceEntity sourceEntity;

    public RecommendSource(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
