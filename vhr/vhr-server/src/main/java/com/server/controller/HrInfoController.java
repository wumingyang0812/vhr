package com.server.controller;

import com.server.pojo.Hr;
import com.server.pojo.RespBean;
import com.server.service.IHrService;
import com.server.service.impl.HrServiceImpl;
import com.server.utils.HrUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/hr")
public class HrInfoController {

    @Autowired
    private IHrService iHrService;

    @ApiOperation(value = "获取个人信息")
    @GetMapping("/info")
    public Hr getCurrentHr() {
        log.info("获取个人信息");
        return HrUtils.getCurrentHr();
    }

    @ApiOperation(value = "更新个人信息")
    @PutMapping("/info")
    public RespBean updateHr(@RequestBody Hr hr, Authentication authentication){
        if(iHrService.updateById(hr)){
            log.info("个人信息更新成功。");
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr,authentication.getCredentials(),authentication.getAuthorities()));
            log.info("客户端个人信息更新成功。");
           return RespBean.ok("更新成功！");
        }
        log.info("个人信息更新失败。");
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "更新密码")
    @PutMapping("/pass")
    public RespBean updatePassword(@RequestBody Map<String,Object> info){

        String oldpass = (String)info.get("oldpass");
        String pass = (String)info.get("pass");
        if(iHrService.updatePassword(oldpass,pass,HrUtils.getCurrentHr().getId())){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
