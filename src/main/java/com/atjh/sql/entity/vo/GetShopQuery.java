package com.atjh.sql.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetShopQuery implements Serializable {
    private String address;

    private Integer shopAdmin;

    private String note;

}
