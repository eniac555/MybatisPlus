package com.atguigu.mpdemo1010.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    // @TableId(type = IdType.AUTO)//设置主键自动增长
    private Long id;
    /*
    AUTO：自动增长

    ID_WORKER：mp自带策略，生成19位，数字类型是使用这种策略，比如long，int
    ID_WORKER_STR：mp自带策略，生成19位，字符串类型是使用这种策略，比如String

    INPUT：自己输入
    NONE：自己输入
    UUID：随机唯一值
     */

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic//逻辑删除注解
    private Integer deleted;

    //@Data lombok会把get set，有参构造，无参构造都生成出来
}
