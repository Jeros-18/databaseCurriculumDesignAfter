package com.atjh.sql.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class getStoreQuery implements Serializable {
    private Integer id;
    private Float size;

    private Integer storeAdmin;

    private String note;

}
