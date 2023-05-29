package com.lzg.gulimall.common.utils;

import java.util.Map;

/**
 * @ClassName: ParamUtil
 * @Description: param提取工具类
 * @author: lzg
 * @date: 2023/5/24 9:48
 */
public class ParamUtil {


    /**
     * 获取查询条件中目标参数
     * @param params
     * @param tar
     * @return
     */
    public static String getParamToString(Map<String, Object> params,String tar){
        return (String)params.get(tar);
    }




}
