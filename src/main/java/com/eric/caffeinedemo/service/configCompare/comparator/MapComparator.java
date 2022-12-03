package com.eric.caffeinedemo.service.configCompare.comparator;

import java.util.Map;

/**
 * <p>
 * 比较器接口
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-12-03
 */
public interface MapComparator {

    /**
     * 比较key
     *
     * @param oldMap 老map
     * @param newMap 新map
     * @return 比较结果
     */
    CompareResult diffKey(Map<String, Object> oldMap, Map<String, Object> newMap);

    /**
     * 比较value
     *
     * @param oldMap 老map
     * @param newMap 新map
     * @return 比较结果
     */
    CompareResult diffValue(Map<String, Object> oldMap, Map<String, Object> newMap);
}
