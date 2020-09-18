package com.cc;

import com.cc.mapper.UserMapper;
import com.cc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper，所有的方法都来自父类
    //我们也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        //参数是一个Wrapper，条件构造器，这里我们先不用，传null
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

}
