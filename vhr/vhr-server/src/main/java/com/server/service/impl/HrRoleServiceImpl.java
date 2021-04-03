package com.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.mapper.HrRoleMapper;
import com.server.pojo.HrRole;
import com.server.service.IHrRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@Service
public class HrRoleServiceImpl extends ServiceImpl<HrRoleMapper, HrRole> implements IHrRoleService {
    @Autowired
    private HrRoleMapper hrRoleMapper;
    @Autowired
    private IHrRoleService iHrRoleService;

    /**
     * 添加操作员的新权限数据
     *
     * @param hrid
     * @param rids
     * @return
     */
    @Override
    public boolean addHrRoles(Integer hrid, Integer[] rids) {
        ArrayList<HrRole> hrRoles = new ArrayList<>();
        for (Integer rid : rids) {
            HrRole hrRole = new HrRole();
            hrRole.setHrid(hrid);
            hrRole.setRid(rid);
            hrRoles.add(hrRole);
        }
        return iHrRoleService.saveBatch(hrRoles);
    }
}
