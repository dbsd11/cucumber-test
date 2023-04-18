package group.bison.cucumber.domain.infrastructure_layer;

import group.bison.cucumber.domain.target.entity.TargetEntity;

import java.util.List;

public interface TargetRepository {

    public TargetEntity getById(Integer id);

    public List<TargetEntity> getAllTargetList(Integer enableStatus);
}