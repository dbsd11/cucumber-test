package group.bison.cucumber.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import group.bison.cucumber.domain.entity.SourceEntity;

@Repository
public interface SourceMapper extends BaseMapper<SourceEntity>{
    
}
