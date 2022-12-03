package com.eric.caffeinedemo.service.configCompare.parse;

import java.io.File;
import java.util.Map;

/**
 * <p>
 * 解析器接口
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-12-03
 */
public interface Parser {

    /**
     * 解析输入文件为键值对
     *
     * @param file 输入文件
     * @return k -> v
     */
    Map<String, Object> parse(File file);

    /**
     * 是否支持
     *
     * @param file 文件
     * @return true/false
     */
    boolean supports(File file);
}
