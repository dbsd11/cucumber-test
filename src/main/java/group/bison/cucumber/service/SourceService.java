package group.bison.cucumber.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import group.bison.cucumber.common.utils.ClassCastUtil;
import group.bison.cucumber.dao.SourceMapper;
import group.bison.cucumber.domain.entity.SourceEntity;
import group.bison.cucumber.domain.model.vo.SourceVO;

@Service
public class SourceService {
    
    @Autowired
    private SourceMapper sourceMapper;

    public List<SourceVO> getAllSourceList(Integer enableStatus) {
        return (List<SourceVO>)sourceMapper.selectList(enableStatus != null ? new QueryWrapper<SourceEntity>().eq("enable_status", enableStatus) : new QueryWrapper<SourceEntity>()).stream().map(entity -> ClassCastUtil.cast(entity, SourceVO.class)).collect(Collectors.toList());
    }
}
