package group.bison.cucumber.domain.target.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import group.bison.cucumber.domain.infrastructure_layer.VO2EntityCaster;
import group.bison.cucumber.domain.target.entity.TargetEntity;
import lombok.Data;

import java.util.Date;

@TableName("target")
@Data
public class TargetVO implements VO2EntityCaster<TargetEntity> {
    
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
