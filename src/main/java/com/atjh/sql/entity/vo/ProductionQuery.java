package com.atjh.sql.entity.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ProductionQuery {
    private Integer id;
    private String name;
    private Integer shopId;
    private Integer storeId;
}
