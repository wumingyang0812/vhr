package com.server.controller;


import com.server.service.impl.HrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/system")
public class HrController {

    @GetMapping("/init")
    public String test(){
        return "test";
    }
}
