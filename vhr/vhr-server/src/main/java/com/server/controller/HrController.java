package com.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.server.pojo.Hr;
import com.server.pojo.HrRole;
import com.server.pojo.RespBean;
import com.server.pojo.Role;
import com.server.service.IHrRoleService;
import com.server.service.IHrService;
import com.server.service.IRoleService;
import com.server.service.impl.HrServiceImpl;
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
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    private IHrService hrService;
    @Autowired
    private IHrRoleService hrRoleService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords) {
        log.info("获取所有操作员");
        return hrService.getAllHrs(keywords);
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id) {

        if (hrRoleService.remove(new QueryWrapper<HrRole>().eq("hrid", id)) && hrService.removeById(id)) {
            log.info("删除操作员：成功");
            return RespBean.ok("删除成功！");
        }
        log.info("删除操作员：失败");
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "更新操作员信息")
    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateById(hr)) {
            log.info("更新操作员信息：成功");
            return RespBean.ok("更新成功！");
        }
        log.info("更新操作员信息：失败");
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (rids == null || rids.length == 0) {
            log.info("更新操作员角色：更新失败：角色不能为空！");
            return RespBean.error("更新失败：角色不能为空！");
        }
        // 删除hrRole表中所有hrid的数据
        hrRoleService.remove(new QueryWrapper<HrRole>().eq("hrid", hrid));
        // 添加操作员的新权限数据
        if (hrRoleService.addHrRoles(hrid, rids)) {
            log.info("更新操作员角色：更新成功");
            return RespBean.ok("更新成功！");
        }
        log.info("更新操作员角色：更新失败");
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        log.info("获取所有角色");
        return roleService.list();
    }

}
