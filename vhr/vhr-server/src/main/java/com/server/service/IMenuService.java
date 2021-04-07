package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenusWithRole();

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByHrId();

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}
