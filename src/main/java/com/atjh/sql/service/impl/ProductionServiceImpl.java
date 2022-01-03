package com.atjh.sql.service.impl;

import com.atjh.sql.entity.Production;
import com.atjh.sql.mapper.ProductionMapper;
import com.atjh.sql.service.ProductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2022-01-04
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

}
