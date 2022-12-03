package com.eric.caffeinedemo.cache;

/**
 * @author Eric Tseng
 * @description CacheRefreshTime
 * @since 2022/11/19 19:57
 */
public class CacheRefreshDuration {
    private static long userCacheRefreshDuration = 120L;

    public static long getUserCacheRefreshDuration() {
        return userCacheRefreshDuration;
    }

    public static void setUserCacheRefreshDuration(long userCacheRefreshDuration) {
        CacheRefreshDuration.userCacheRefreshDuration = userCacheRefreshDuration;
    }
}
