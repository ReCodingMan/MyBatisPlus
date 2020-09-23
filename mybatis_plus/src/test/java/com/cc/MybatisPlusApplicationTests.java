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

    // 测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("狂神说java");
        user.setAge(3);
        user.setEmail("1111@22222");

        int insert = userMapper.insert(user);// 自动生成ID
        System.out.println(user);
        System.out.println(insert);
    }

    // 更新操作
    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("狂神说java");
        user.setAge(100);
        user.setEmail("1111@22222");
        user.setId(4L);

        int i = userMapper.updateById(user);
        System.out.println(i);
    }



}
