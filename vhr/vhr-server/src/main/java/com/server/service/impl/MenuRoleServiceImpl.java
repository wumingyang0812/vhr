package com.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.mapper.MenuRoleMapper;
import com.server.pojo.MenuRole;
import com.server.pojo.RespBean;
import com.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (mids == null || mids.length == 0) {
            return RespBean.ok("删除成功！");
        }
       if(menuRoleMapper.insertRecord(rid,mids)>0){
           return RespBean.ok("更新成功！");
       }
        return RespBean.error("更新失败！");
    }
}
