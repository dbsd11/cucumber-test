package group.bison.cucumber.domain.target.entity;

import group.bison.cucumber.domain.abstract_layer.BaseEntity;

import java.util.Map;

public class TargetEntity extends BaseEntity implements AutoCloseable {

    private Map param;
    private int initCount = 0;

    public void init(Map param) {
        this.param = param;
        this.initCount ++;
    }

    public boolean hasInited() {
        return initCount > 0;
    }

    @Override
    public void close() throws Exception {
        this.param = null;
        this.initCount --;
    }
}
