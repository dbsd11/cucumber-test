package group.bison.cucumber.rest.service;

import group.bison.cucumber.domain.adaptor_layer.SourceSaveService;
import group.bison.cucumber.domain.adaptor_layer.external.StorageService;
import group.bison.cucumber.domain.infrastructure_layer.SourceRepository;
import group.bison.cucumber.domain.infrastructure_layer.TargetRepository;
import group.bison.cucumber.domain.message_layer.StorageMessageProducer;
import group.bison.cucumber.domain.source.entity.SourceEntity;
import group.bison.cucumber.domain.target.entity.TargetEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SourceSaveServiceImpl implements SourceSaveService {

    @Autowired
    private StorageService<InputStream> storageService;

    @Autowired
    private StorageMessageProducer storageMessageProducer;

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public List<TargetEntity> getMatchStorageTargetList(SourceEntity sourceEntity) {
        if (sourceEntity.getData() == null) {
            return Collections.emptyList();
        }

        List<TargetEntity> targetEntityList = targetRepository.getAllTargetList(1);
        return CollectionUtils.isEmpty(targetEntityList) ? Collections.emptyList() : targetEntityList.stream().filter(targetEntity -> {
            return Objects.equals(targetEntity.getFieldValue("type"), 0) && (sourceEntity.getData() instanceof String);
        }).collect(Collectors.toList());
    }

    @Override
    public void save2Target(SourceEntity sourceEntity, TargetEntity targetEntity) throws Exception {
        if (sourceEntity.getData() == null) {
            log.info("no need to save empty source data");
            return;
        }

        String storageInfo = null;
        try (SourceEntity sourceEntity2 = sourceEntity; TargetEntity targetEntity2 = targetEntity) {
            Object data = sourceEntity.getData();
            storageInfo = storageService.storage((Integer) targetEntity.getFieldValue("id"), new ByteArrayInputStream(String.valueOf(data).getBytes()));
        }

        if (StringUtils.containsIgnoreCase(storageInfo, "success")) {
            storageMessageProducer.sendSuccess((Integer) sourceEntity.getFieldValue("id"), (Integer) targetEntity.getFieldValue("id"), storageInfo);
        } else {
            storageMessageProducer.sendFailMsg((Integer) sourceEntity.getFieldValue("id"), (Integer) targetEntity.getFieldValue("id"), storageInfo);
        }
    }
}
