package group.bison.cucumber.suite.source.content;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.model.vo.SourceVO;

public abstract class ContentSource implements Callable {
    
    private SourceVO sourceVO;

    public ContentSource(SourceVO sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
