package group.bison.cucumber.suite.source.news;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.model.vo.SourceVO;

public abstract class NewsSource implements Callable {
    
    private SourceVO sourceVO;

    public NewsSource(SourceVO sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
