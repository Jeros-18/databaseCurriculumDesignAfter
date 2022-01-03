package com.atjh.sql.controller;


import com.atjh.sql.entity.Employee;
import com.atjh.sql.entity.Store;
import com.atjh.sql.entity.vo.getStoreQuery;
import com.atjh.sql.service.StoreService;
import com.atjh.sql.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2021-12-16
 */
@Api(description = "仓库管理")
@RestController
@RequestMapping("/sql/store")
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "所有仓库列表")
    @GetMapping
    public R getAllStore(){
        List<Store> list = storeService.list(null);
        return R.ok().data("storeList",list);
    }

    @ApiOperation(value = "根据Id查询仓库")
    @GetMapping("getStore/{id}")
    public R getStoreById(@PathVariable String id){
        Store store = storeService.getById(id);
        return R.ok().data("store",store);
    }

    @ApiOperation(value = "根据id删除仓库")
    @DeleteMapping("{id}")
    public R deleteStore(@PathVariable Integer id){
        boolean remove = storeService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加仓库")
    @PostMapping
    public R addStore(@RequestBody Store store){
        boolean save = storeService.save(store);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id修改仓库")
    @PutMapping("{id}")
    public R updateStore(
            @PathVariable Integer id,
            @RequestBody Store store){
        store.setId(id);
        boolean update = storeService.updateById(store);
        if (update) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "带条件分页查询仓库列表")
    @PostMapping("getStore/{current}/{limit}")
    public R getStorePageVo(@PathVariable Long current,
                               @PathVariable Long limit,
                               @RequestBody getStoreQuery getStoreQuery,
                            HttpServletRequest request){
        Integer id = getStoreQuery.getId();
        Float size = getStoreQuery.getSize();
        Integer storeAdmin = getStoreQuery.getStoreAdmin();
        String note = getStoreQuery.getNote();

        String sizeMin = request.getParameter("sizeMin");
        String sizeMax = request.getParameter("sizeMax");
        System.out.println("sizeMax---------------------------------------------------------"+sizeMax);

        /**
         * 原符号 < <= > >= <>
         * 对应函数 lt() le() gt() ge() ne()
         */
        QueryWrapper<Store> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id",id);
        }
        if (!StringUtils.isEmpty(sizeMax)) {
            wrapper.le("size",sizeMax);
        }
        if (!StringUtils.isEmpty(sizeMin)) {
            wrapper.ge("size",sizeMin);
        }
        if (!StringUtils.isEmpty(storeAdmin)) { //查不出来
            wrapper.eq("store_admin",storeAdmin);
        }
        if (!StringUtils.isEmpty(note)) {
            wrapper.like("note",note);
        }

        Page<Store> page = new Page<>(current, limit);
        storeService.page(page, wrapper);

        List<Store> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

