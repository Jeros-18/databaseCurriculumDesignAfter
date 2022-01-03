package com.atjh.sql.controller;


import com.atjh.sql.entity.Factory;
import com.atjh.sql.entity.vo.FactoryQuery;
import com.atjh.sql.service.FactoryService;
import com.atjh.sql.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
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
 * @since 2021-12-17
 */
@Api(description = "工厂管理")
@RestController
@RequestMapping("/sql/factory")
@CrossOrigin
public class FactoryController {
    @Autowired
    private FactoryService factoryService;

    @ApiOperation(value = "所有工厂列表")
    @GetMapping
    public R getAllFactory(){
        List<Factory> list = factoryService.list(null);
        return R.ok().data("factoryList",list);
    }

    @ApiOperation(value = "根据id查询工厂")
    @GetMapping("getFactory/{id}")
    public R getFactoryById(@PathVariable Integer id){
        Factory factory = factoryService.getById(id);
        return R.ok().data("factory",factory);
    }

    @ApiOperation(value = "删除工厂")
    @DeleteMapping("{id}")
    public R deleteFactory(@PathVariable Integer id){
        boolean remove = factoryService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改工厂")
    @PutMapping("{id}")
    public R updateFactory(
            @PathVariable Integer id,
            @RequestBody Factory Factory){
        Factory.setId(id);
        boolean update = factoryService.updateById(Factory);
        if (update) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加工厂")
    @PostMapping
    public R addFactory(@RequestBody Factory Factory){
        boolean save = factoryService.save(Factory);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    // bug
    @ApiOperation(value = "带条件分页查询工厂列表")
    @PostMapping("getFactory/{current}/{limit}")
    public R getFactoryPageVo(@PathVariable Long current,
                            @PathVariable Long limit,
                            @RequestBody FactoryQuery factoryQuery){

        String faName = factoryQuery.getFaName();
        String dirName = factoryQuery.getDirName();
        String faAddress = factoryQuery.getFaAddress();
        String id = factoryQuery.getId();

        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.like("id",id);
        }
        if (!StringUtils.isEmpty(faName)) {
            wrapper.like("fa_name",faName);
        }
        if (!StringUtils.isEmpty(dirName)) {
            wrapper.like("dir_name",dirName);
        }
        if (!StringUtils.isEmpty(faAddress)) {
            wrapper.like("fa_address",faAddress);
        }

        Page<Factory> page = new Page<>(current, limit);
        factoryService.page(page, wrapper);

        List<Factory> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

