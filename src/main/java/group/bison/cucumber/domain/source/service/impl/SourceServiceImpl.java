package group.bison.cucumber.domain.source.service.impl;

import group.bison.cucumber.domain.infrastructure_layer.SourceRepository;
import group.bison.cucumber.domain.source.entity.SourceEntity;
import group.bison.cucumber.domain.source.service.SourceService;

import javax.annotation.Resource;
import java.util.List;

public class SourceServiceImpl implements SourceService {

    @Resource
    private SourceRepository sourceRepository;

    @Override
    public List<SourceEntity> getAllSources() {
        return sourceRepository.getAllSourceList(1);
    }
}
