package com.atjh.sql.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoreadminQuery implements Serializable {
    private String name;

    private String tell;

    private String storeId;
}
