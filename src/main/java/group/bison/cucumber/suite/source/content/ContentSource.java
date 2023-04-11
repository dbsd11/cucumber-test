package group.bison.cucumber.suite.source.content;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.source.entity.SourceEntity;

public abstract class ContentSource implements Callable {
    
    private SourceEntity sourceVO;

    public ContentSource(SourceEntity sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
