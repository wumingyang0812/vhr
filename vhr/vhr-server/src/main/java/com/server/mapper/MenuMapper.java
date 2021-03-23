package com.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

//    @Select("select m.*,r.`id` as rid,r.`name` as rname,r.`nameZh` as rnameZh from menu m,menu_role mr,role r where m.`id`=mr.`mid` and mr.`rid`=r.`id` order by m.`id`")
//    List<Menu> getAllMenusWithRole();
}
