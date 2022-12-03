package com.eric.caffeinedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.caffeinedemo.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@Service
public interface IUserService extends IService<User> {
    User getUserById(String id);
}
