package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Hr;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新密码
     * @param oldpass
     * @param pass
     * @param id
     * @return
     */
    boolean updatePassword(String oldpass, String pass, Integer id);
}
