package group.bison.cucumber.suite.source.news;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.source.entity.SourceEntity;

public abstract class NewsSource implements Callable {
    
    private SourceEntity sourceVO;

    public NewsSource(SourceEntity sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
