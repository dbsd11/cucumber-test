package group.bison.cucumber.domain.source.service;

import group.bison.cucumber.domain.source.entity.SourceEntity;

import java.util.List;

public interface SourceService {

    public List<SourceEntity> getAllSources();
}
