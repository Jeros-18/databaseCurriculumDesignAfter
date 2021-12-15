package com.atjh.sql.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeQuery implements Serializable {
    private String name;

    private String tell;

    private String address;

    private Integer shopId;

}
