package com.atjh.sql.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class FactoryQuery implements Serializable {
    private String id;
    private String faName;

    private String dirName;

    private String faAddress;
}
