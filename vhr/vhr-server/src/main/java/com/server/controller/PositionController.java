package com.server.controller;


import com.server.pojo.Position;
import com.server.pojo.RespBean;
import com.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/")
    public List<Position> getAllPosition() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        position.setEnabled(true);
        position.setCreatedate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deleteById(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deleteByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


}
