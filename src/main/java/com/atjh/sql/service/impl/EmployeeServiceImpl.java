package com.atjh.sql.service.impl;

import com.atjh.sql.entity.Employee;
import com.atjh.sql.mapper.EmployeeMapper;
import com.atjh.sql.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2021-12-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
