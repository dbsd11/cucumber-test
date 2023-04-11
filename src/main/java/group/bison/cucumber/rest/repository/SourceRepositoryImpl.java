package group.bison.cucumber.rest.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import group.bison.cucumber.domain.infrastructure_layer.SourceRepository;
import group.bison.cucumber.domain.source.entity.SourceEntity;
import group.bison.cucumber.domain.source.vo.SourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SourceRepositoryImpl implements SourceRepository {

    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<SourceEntity> getAllSourceList(Integer enableStatus) {
        return (List<SourceEntity>)sourceMapper.selectList(enableStatus != null ? new QueryWrapper<SourceVO>().eq("enable_status", enableStatus) : new QueryWrapper<SourceVO>()).stream().map(SourceVO::tryCast2Entity).collect(Collectors.toList());
    }

    @Component
    public interface SourceMapper extends BaseMapper<SourceVO> {
    }
}
