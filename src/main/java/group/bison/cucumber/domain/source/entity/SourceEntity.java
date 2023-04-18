package group.bison.cucumber.domain.source.entity;

import group.bison.cucumber.domain.abstract_layer.BaseEntity;

import java.util.Map;

public class SourceEntity extends BaseEntity implements AutoCloseable {

    private int initCount = 0;
    private Map param;
    private Object data;

    public void init(Map param) {
        this.param = param;
        this.initCount ++;
    }

    public void tempSave(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public boolean hasInited() {
        return initCount > 0;
    }

    @Override
    public void close() throws Exception {
        this.param = null;
        this.data = null;
        this.initCount --;
    }
}
