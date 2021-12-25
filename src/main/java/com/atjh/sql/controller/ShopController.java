package com.atjh.sql.controller;


import com.atjh.sql.entity.Shop;
import com.atjh.sql.entity.vo.GetShopQuery;
import com.atjh.sql.service.ShopService;
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
@Api(description = "车间管理" )
@RestController
@RequestMapping("/sql/shop")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "所有车间列表")
    @GetMapping
    public R getAllShop(){
        List<Shop> list = shopService.list(null);
        return R.ok().data("shopList",list);
    }

    @ApiOperation(value = "根据id查询车间")
    @GetMapping("getShop/{id}")
    public R getShopById(@PathVariable Integer id){
        Shop shop = shopService.getById(id);
        return R.ok().data("shop",shop);
    }

    @ApiOperation(value = "删除车间")
    @DeleteMapping("{id}")
    public R deleteShop(@PathVariable Integer id){
        boolean remove = shopService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改车间")
    @PutMapping("{id}")
    public R updateShop(
            @PathVariable Integer id,
            @RequestBody Shop shop){
        shop.setId(id);
        boolean update = shopService.updateById(shop);
        if (update) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加车间")
    @PostMapping
    public R addShop(@RequestBody Shop shop){
        boolean save = shopService.save(shop);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "带条件分页查询车间列表")
    @PostMapping("getShop/{current}/{limit}")
    public R getStorePageVo(@PathVariable Long current,
                            @PathVariable Long limit,
                            @RequestBody GetShopQuery shopQuery){

        String address = shopQuery.getAddress();
        Integer shopAdmin = shopQuery.getShopAdmin();
        String note = shopQuery.getNote();


        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(address)) {
            wrapper.like("address",address);
        }
        if (!StringUtils.isEmpty(shopAdmin)) {
            wrapper.like("shopAdmin",shopAdmin);
        }
        if (!StringUtils.isEmpty(note)) {
            wrapper.like("note",note);
        }

        Page<Shop> page = new Page<>(current, limit);
        shopService.page(page, wrapper);

        List<Shop> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

