package com.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.server.pojo.Department;
import com.server.pojo.Employee;
import com.server.pojo.RespBean;
import com.server.service.IDepartmentService;
import com.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@Log4j2
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartmentById(@PathVariable Integer id) {
        log.info("删除部门，id："+id);
        return departmentService.deleteDep(id);
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department) {
        log.info("添加部门 Department: "+department);
        return departmentService.addDep(department);
    }
}
