package com.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.mapper.HrRoleMapper;
import com.server.mapper.MenuMapper;
import com.server.mapper.MenuRoleMapper;
import com.server.mapper.RoleMapper;
import com.server.pojo.*;
import com.server.service.IMenuService;
import com.server.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    MenuMapper menuMapper;
    @Resource
    MenuRoleMapper menuRoleMapper;
    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Menu> getAllMenusWithRole() {
        List<Menu> menus = menuMapper.selectList(null);
        for (Menu menu : menus) {
            List<MenuRole> mids = menuRoleMapper.selectList(new QueryWrapper<MenuRole>().eq("mid", menu.getId()));
            if (mids.size() == 0) {
                menu.setRoles(null);
                continue;
            }
            List<Integer> rids = new ArrayList<>();
            for (MenuRole menuRole : mids) {
                rids.add(menuRole.getRid());
            }
            List<Role> roles = roleMapper.selectBatchIds(rids);
            menu.setRoles(roles);
        }
        return menus;
    }


    /**
     * 通过用户id获取该用户所有的菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {

        return menuMapper.getAllMenus();
    }


}
