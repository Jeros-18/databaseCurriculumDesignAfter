package com.atjh.sql.controller;


import com.atjh.sql.entity.Employee;
import com.atjh.sql.entity.Factory;
import com.atjh.sql.entity.Shop;
import com.atjh.sql.entity.Storeadmin;
import com.atjh.sql.entity.vo.EmployeeQuery;
import com.atjh.sql.entity.vo.StoreadminQuery;
import com.atjh.sql.service.StoreadminService;
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
@Api(description = "仓库管理员管理")
@RestController
@RequestMapping("/sql/storeadmin")
@CrossOrigin
public class StoreadminController {

    @Autowired 
    private StoreadminService storeadminService;

    @ApiOperation(value = "所有仓库管理员列表")
    @GetMapping
    public R getAllStoreadmin(){
        List<Storeadmin> list = storeadminService.list(null);
        return R.ok().data("storeAdmin",list);
}

    @ApiOperation(value = "根据id查询仓库管理员")
    @GetMapping("getStoreAdmin/{id}")
    public R getStoreadminById(@PathVariable Integer id){
        Storeadmin storeadmin = storeadminService.getById(id);
        return R.ok().data("storeAdmin",storeadmin);
    }

    @ApiOperation(value = "删除仓库管理员")
    @DeleteMapping("{id}")
    public R deleteStoreadmin(@PathVariable Integer id){
        boolean remove = storeadminService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改仓库管理员")
    @PutMapping("{id}")
    public R updateStoreadmin(
            @PathVariable Integer id,
            @RequestBody Storeadmin Storeadmin){
        Storeadmin.setId(id);
        boolean update = storeadminService.updateById(Storeadmin);
        if (update) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加车间")
    @PostMapping
    public R addShop(@RequestBody Storeadmin storeadmin){
        boolean save = storeadminService.save(storeadmin);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    // bug
    @ApiOperation(value = "带条件分页查询仓库管理员列表")
    @PostMapping("getStoreAdmin/{current}/{limit}")
    public R getEmployeePageVo(@PathVariable Long current,
                               @PathVariable Long limit,
                               @RequestBody StoreadminQuery storeadminQuery ){
        String name = storeadminQuery.getName();
        String tell = storeadminQuery.getTell();
        String storeId = storeadminQuery.getStoreId();

        QueryWrapper<Storeadmin> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("name",name);
        }
        if (!StringUtils.isEmpty(tell)) {
            wrapper.like("tell",tell);
        }
        if (!StringUtils.isEmpty(storeId)) {
            wrapper.eq("storeId",storeId);
        }


        Page<Storeadmin> page = new Page<>(current, limit);
        storeadminService.page(page,wrapper);

        List<Storeadmin> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

