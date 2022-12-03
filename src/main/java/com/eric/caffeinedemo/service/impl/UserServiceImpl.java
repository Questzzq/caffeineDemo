package com.eric.caffeinedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.caffeinedemo.cache.RedisDataAsyncCache;
import com.eric.caffeinedemo.entity.User;
import com.eric.caffeinedemo.mapper.UserMapper;
import com.eric.caffeinedemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisDataAsyncCache redisDataAsyncCache;

    @Override
    public User getUserById(String id) {
        User user = redisDataAsyncCache.get(id);
        if(user == null) {
            return userMapper.getUserById(id);
        }
        return user;
    }
}
