package com.cc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * 在对应的mapper上，继承基本的类 BaseMapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD都完成了
    //不需要像以前那样的配置一大堆文件
}
