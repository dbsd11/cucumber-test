package group.bison.cucumber.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import group.bison.cucumber.domain.Entity2DOCaster;
import group.bison.cucumber.domain.model.vo.SourceVO;
import lombok.Data;

@TableName("source")
@Data
public class SourceEntity implements Entity2DOCaster<SourceVO> {
    
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
