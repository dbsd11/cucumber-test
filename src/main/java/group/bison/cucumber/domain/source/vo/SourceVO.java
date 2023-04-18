package group.bison.cucumber.domain.source.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import group.bison.cucumber.domain.infrastructure_layer.VO2EntityCaster;
import group.bison.cucumber.domain.source.entity.SourceEntity;
import lombok.Data;

import java.util.Date;

@TableName("source")
@Data
public class SourceVO implements VO2EntityCaster<SourceEntity> {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private Integer type;

    private String ext;

    private Integer enableStatus;

    private Date createTimestamp;

    private Date updateTimestamp;
}
