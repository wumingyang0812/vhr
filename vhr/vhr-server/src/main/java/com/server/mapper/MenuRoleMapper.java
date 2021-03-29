package com.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mingyang
 * @since 2021-03-19
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    // 通过用户角色id查询所有的menu
    @Select("select mid from menu_role where rid=#{rid}")
    List<Integer> getAllMenusByRid(Integer rid);
}
