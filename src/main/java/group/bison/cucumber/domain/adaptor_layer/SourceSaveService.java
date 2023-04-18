package group.bison.cucumber.domain.adaptor_layer;

import group.bison.cucumber.domain.source.entity.SourceEntity;
import group.bison.cucumber.domain.target.entity.TargetEntity;

import java.util.List;

public interface SourceSaveService {

    public List<TargetEntity> getMatchStorageTargetList(SourceEntity sourceEntity);

    public void save2Target(SourceEntity sourceEntity, TargetEntity targetEntity) throws Exception;
}
