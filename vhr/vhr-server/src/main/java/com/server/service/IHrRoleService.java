package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.HrRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
public interface IHrRoleService extends IService<HrRole> {
    /**
     * 添加操作员的新权限数据
     * @param hrid
     * @param rids
     * @return
     */
    boolean addHrRoles(Integer hrid, Integer[] rids);
}
