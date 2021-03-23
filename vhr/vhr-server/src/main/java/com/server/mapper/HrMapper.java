package com.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.pojo.Hr;
import com.server.pojo.Role;
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
public interface HrMapper extends BaseMapper<Hr> {

    @Select("select * from role r, hr_role hrr where r.id=hrr.rid and hrr.hrid=#{id}")
    List<Role> getHrRoles(Integer id);

}
