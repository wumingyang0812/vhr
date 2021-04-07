package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Department;
import com.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     *添加部门
     * @param department
     */
    RespBean addDep(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
