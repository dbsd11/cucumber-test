package group.bison.cucumber.domain.infrastructure_layer;

import group.bison.cucumber.domain.source.entity.SourceEntity;

import java.util.List;

public interface SourceRepository {

    public List<SourceEntity> getAllSourceList(Integer enableStatus);
}
