package com.atjh.sql.controller;


import com.atjh.sql.entity.Production;
import com.atjh.sql.entity.vo.ProductionQuery;
import com.atjh.sql.service.ProductionService;
import com.atjh.sql.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2022-01-04
 */
@RestController
@RequestMapping("/sql/production")
@CrossOrigin
public class ProductionController {
    @Autowired
    private ProductionService productionService;

    @ApiOperation(value = "所有产品列表")
    @GetMapping
    public R getAllFactory(){
        List<Production> list = productionService.list(null);
        return R.ok().data("productionList",list);
    }

    @ApiOperation(value = "根据id查询产品")
    @GetMapping("getProduction/{id}")
    public R getFactoryById(@PathVariable Integer id){
        Production production = productionService.getById(id);
        return R.ok().data("production",production);
    }

    @ApiOperation(value = "删除产品")
    @DeleteMapping("{id}")
    public R deleteFactory(@PathVariable Integer id){
        boolean remove = productionService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改产品")
    @PutMapping("{id}")
    public R updateFactory(
            @PathVariable Integer id,
            @RequestBody Production production){
        production.setId(id);
        boolean update = productionService.updateById(production);
        if (update) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加产品")
    @PostMapping
    public R addFactory(@RequestBody Production Factory){
        boolean save = productionService.save(Factory);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    // bug
    @ApiOperation(value = "带条件分页查询产品列表")
    @PostMapping("getProduction/{current}/{limit}")
    public R getFactoryPageVo(@PathVariable Long current,
                              @PathVariable Long limit,
                              @RequestBody ProductionQuery productionQuery){

        String name = productionQuery.getName();
        Integer shopId = productionQuery.getShopId();
        Integer storeId = productionQuery.getStoreId();
        Integer id = productionQuery.getId();


        QueryWrapper<Production> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("id",id);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(shopId)) {
            wrapper.eq("shopId",shopId);
        }
        if (!StringUtils.isEmpty(storeId)) {
            wrapper.eq("storeId",storeId);
        }

        Page<Production> page = new Page<>(current, limit);
        productionService.page(page, wrapper);

        List<Production> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

