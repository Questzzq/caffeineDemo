package com.eric.caffeinedemo.cache;

import com.eric.caffeinedemo.entity.User;
import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Tseng
 * @description 异步缓存
 * @since 2022/11/19 19:39
 */
@Slf4j
@Component
public class RedisDataAsyncCache {
    RedisDataAsyncCache() {
        init();
    }

    private AsyncLoadingCache<String, User> userCache = null;

    public void init() {
        userCache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(120, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, User>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable User value, @NonNull RemovalCause removalCause) {
                        System.out.println("expire la ");
                    }
                })
                .buildAsync(initLoader());
    }

    public AsyncLoadingCache<String, User> getCache() {
        return userCache;
    }

    private AsyncCacheLoader<String, User> initLoader() {
        return new AsyncCacheLoader<String, User>() {
            @Override
            public @NonNull
            CompletableFuture<User> asyncLoad(@NonNull String key, @NonNull Executor executor) {
                System.out.println("i come load ");
                return CompletableFuture.supplyAsync(() -> {
                    return new User(key, "Name_" + key);
                }, executor);
            }

            @Override
            public @NonNull
            CompletableFuture<User> asyncReload(@NonNull String key, @NonNull User oldValue, @NonNull Executor executor) {
                System.out.println("i come reload ");
                return CompletableFuture.supplyAsync(() -> {
                    return new User(key, "oldName_" + key);
                }, executor);

            }

        };
    }

    public User get(String id){
        try {
            return userCache.get(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
