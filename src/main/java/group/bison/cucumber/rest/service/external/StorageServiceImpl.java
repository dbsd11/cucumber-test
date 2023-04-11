package group.bison.cucumber.rest.service.external;

import group.bison.cucumber.domain.adaptor_layer.external.StorageService;
import group.bison.cucumber.domain.infrastructure_layer.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StorageServiceImpl implements StorageService<InputStream> {

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public String storage(Integer targetId, InputStream data) {
        return null;
    }
}
