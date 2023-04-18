package group.bison.cucumber.suite.source.maps;

import java.util.concurrent.Callable;

import group.bison.cucumber.domain.source.entity.SourceEntity;

public abstract class MapSource implements Callable {
    
    private SourceEntity sourceVO;

    public MapSource(SourceEntity sourceVO) {
        this.sourceVO = sourceVO;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
