package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Hr;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
public interface IHrService extends IService<Hr> {
    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Hr> getAllHrs(String keywords);


}
