package group.bison.cucumber.suite.source.rss;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.model.vo.SourceVO;

public abstract class RecommendSource implements Callable {
    
    private SourceVO sourceVO;

    public RecommendSource(SourceVO sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
