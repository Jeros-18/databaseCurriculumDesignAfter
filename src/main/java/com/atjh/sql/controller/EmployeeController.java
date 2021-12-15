package com.atjh.sql.controller;


import com.atjh.sql.entity.Employee;
import com.atjh.sql.entity.vo.EmployeeQuery;
import com.atjh.sql.service.EmployeeService;
import com.atjh.sql.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2021-12-14
 */
@Api(description="员工管理")
@RestController
@RequestMapping("/sql/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "所有员工列表")
    @GetMapping
    public R getAllEmployee(){
        List<Employee> list = employeeService.list(null);
        return R.ok().data("employeeList",list);
    }

    @ApiOperation(value = "根据id删除员工")
    @DeleteMapping("{id}")
    public R delTeacher(@PathVariable String id){
        boolean remove = employeeService.removeById(id);
        if (remove) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("addEmployee")
    public R addEmployee(@RequestBody Employee employee){
        boolean save = employeeService.save(employee);
        if (save) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据Id查询员工")
    @GetMapping("getEmployee/{id}")
    public R getEmployeeById(@PathVariable String id){
        Employee employee = employeeService.getById(id);
        return R.ok().data("employee",employee);
    }

    @ApiOperation(value = "修改员工")
    @PostMapping("updateEmployee")
    public R updateEmployee(@RequestBody Employee employee){
        boolean updateById = employeeService.updateById(employee);
        if (updateById) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "带条件分页查询员工列表")
    @PostMapping("getEmployee/{current}/{limit}")
    public R getEmployeePageVo(@PathVariable Long current,
                               @PathVariable Long limit,
                               @RequestBody EmployeeQuery employeeQuery){
        String name = employeeQuery.getName();
        String tell = employeeQuery.getTell();
        String address = employeeQuery.getAddress();
        Integer shopId = employeeQuery.getShopId();

        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(tell)) {
            wrapper.like("tell",tell);
        }
        if (!StringUtils.isEmpty(address)) {
            wrapper.like("address",address);
        }
        if (!StringUtils.isEmpty(address)) {
            wrapper.like("shopId",shopId);
        }

        Page<Employee> page = new Page<>(current, limit);
        employeeService.page(page,wrapper);

        List<Employee> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

