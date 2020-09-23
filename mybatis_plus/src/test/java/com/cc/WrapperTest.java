package com.cc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.mapper.UserMapper;
import com.cc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空的用户，邮箱不为空的用户，年龄大于等于12岁
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("email").isNotNull("name").ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2() {
        //查询名字2的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "2");
        System.out.println(userMapper.selectOne(wrapper));
    }

    @Test
    void test3() {
        //查询年龄在0-20
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 0, 20);
        System.out.println(userMapper.selectOne(wrapper));
    }

    @Test
    void test4() {
        //模糊查询，名字中不包含 e
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //左和右
        wrapper
                .notLike("name", "e")
                .likeRight("email", "t"); // t%

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test5() {
        //模糊查询，名字中不包含 e
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //id在子查询查出来的
        wrapper.inSql("id", "select id from user where id<3");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test6() {
        //模糊查询，名字中不包含 e
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过id进行排序
        wrapper.orderByDesc("id");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
}
