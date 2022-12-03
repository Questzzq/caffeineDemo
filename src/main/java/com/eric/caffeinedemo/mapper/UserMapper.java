package com.eric.caffeinedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eric.caffeinedemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserById(String id);
}
