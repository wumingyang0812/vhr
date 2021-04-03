package com.server.utils;

import com.server.pojo.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
    /**
     * 获取当前操作员
     * @return
     */
    public static Hr getCurrentHr(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
