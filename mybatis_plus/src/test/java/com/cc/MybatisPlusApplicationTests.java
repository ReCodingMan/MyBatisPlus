package com.cc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.mapper.UserMapper;
import com.cc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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

    //测试乐观锁。成功
    @Test
    public void testOptimisticLocker() {
        //1、查询用户信息
        User user = userMapper.selectById(1L);
        //2、修改用户信息
        user.setName("optimistic");
        user.setAge(999);
        //3、执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁。失败
    @Test
    public void testOptimisticLockerFail() {
        //1、线程一
        User user = userMapper.selectById(1L);
        user.setName("optimistic");
        user.setAge(999);

        //模拟一个线程插入操作
        User user2 = userMapper.selectById(1L);
        user2.setName("optimisticFail");
        user2.setAge(888);

        userMapper.updateById(user2);

        //没有更新，可以使用自旋锁来提交
        userMapper.updateById(user);
    }

    //查询操作
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //查询多个操作
    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //测试条件查询
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","狂神说java");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage() {
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

}
