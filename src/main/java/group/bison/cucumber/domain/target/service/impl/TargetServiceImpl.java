package group.bison.cucumber.domain.target.service.impl;

import group.bison.cucumber.domain.infrastructure_layer.TargetRepository;
import group.bison.cucumber.domain.target.entity.TargetEntity;
import group.bison.cucumber.domain.target.service.TargetService;

import javax.annotation.Resource;

public class TargetServiceImpl implements TargetService {

    @Resource
    private TargetRepository targetRepository;

    @Override
    public TargetEntity getById(Integer id) {
        return targetRepository.getById(id);
    }
}
