package com.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.mapper.HrMapper;
import com.server.mapper.HrRoleMapper;
import com.server.mapper.RoleMapper;
import com.server.pojo.Hr;
import com.server.pojo.HrRole;
import com.server.pojo.Role;
import com.server.service.IHrService;
import com.server.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService,UserDetailsService {

    @Resource
    HrMapper hrMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库，判断用户名是否存在，如果不存在，就抛出UsernameNotFoundException异常
        Hr hr = hrMapper.selectOne(new QueryWrapper<Hr>().eq("username", username));
        if (hr==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 如果用户名存在，通过hr的id查询该用户的所有权限，并存入hr对象中
        hr.setRoles(hrMapper.getHrRoles(hr.getId()));
        System.out.println("HR: "+hr);
        return hr;
    }
    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }


}
