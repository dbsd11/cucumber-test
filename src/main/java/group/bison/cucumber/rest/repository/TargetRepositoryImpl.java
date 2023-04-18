package group.bison.cucumber.rest.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import group.bison.cucumber.domain.infrastructure_layer.TargetRepository;
import group.bison.cucumber.domain.target.entity.TargetEntity;
import group.bison.cucumber.domain.target.vo.TargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TargetRepositoryImpl implements TargetRepository {

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public TargetEntity getById(Integer id) {
        TargetVO targetVO = targetMapper.selectById(id);
        return targetVO == null ? null : targetVO.tryCast2Entity();
    }

    @Override
    public List<TargetEntity> getAllTargetList(Integer enableStatus) {
        return (List<TargetEntity>) targetMapper.selectList(enableStatus != null ? new QueryWrapper<TargetVO>().eq("enable_status", enableStatus) : new QueryWrapper<TargetVO>()).stream().map(TargetVO::tryCast2Entity).collect(Collectors.toList());
    }

    @Component
    public interface TargetMapper extends BaseMapper<TargetVO> {
    }
}
