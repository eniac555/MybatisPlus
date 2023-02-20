package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class Mpdemo1010ApplicationTests {



    @Autowired
    private UserMapper userMapper;

    //查询user表中的所有数据
    @Test
   public void findAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }


    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("Tom");
        user.setAge(38);
        user.setEmail("Tom@qq.com");
        int insert = userMapper.insert(user);//影响的行数，也就是添加了几条记录数
        System.out.println("insert "+insert);

        //没有设置ID，所以系统会自动生成一个19位的id，mybatis-plus自带的默认生成方法
        //可以设置主键自动增长

    }


    //修改操作
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1627127424302510081L);
        user.setAge(200);
        int row = userMapper.updateById(user);//影响的行数
        System.out.println(row);
    }

    //测试乐观锁
    @Test
    public void OptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1627141517235294210L);
        user.setAge(2000);
        int row = userMapper.updateById(user);//影响的行数
        System.out.println(row);
    }


    //多个id的批量查询
    @Test
    public void testSelectDemo1(){
        List<User> list = userMapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        System.out.println(list);
    }


    //简单的条件查询
    @Test
    public void testSelectByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }


    //分页查询
    @Test
    public void testPage(){
        //1.创建page对象
        //传入两个参数：第一个表示当前页，第二个表示每页的记录数
        Page<User> page = new Page<>(1,3);

        //调用mp中的分页查询方法
        //调用mp分页查询过程中，底层会封装
        //把所有的数据封装到page对象里面去
        userMapper.selectPage(page,null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//获取当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());// 3，前面设置的每页记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数
        System.out.println(page.hasNext());//boolean，是否有下页
        System.out.println(page.hasPrevious());//boolean，是否有上页


    }


    //根据id删除记录，物理删除（真正删除）
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1627185907995525122L);
        System.out.println(result);
    }
    //设置标志符deleted后，标志符变成1


    //根据id删除记录，批量物理删除（真正删除）
    @Test
    public void testDeleteBatchId(){
        int result = userMapper.deleteBatchIds(Arrays.asList(2,3));
        System.out.println(result);
    }


    //简单的条件查询删除（物理删除）
    @Test
    public void testDeleteByMap() {

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String,Integer> map1 = new HashMap<>();
        map.put("name", "Lucy");
        map.put("age", 30);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }


    //mp实现复杂查询操作
    @Test
    public void testSelectQuery() {

        //创建对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件和值
        //ge、gt、le、lt  大于、大于等于、小于、小于等于
        //查询age>=30
        wrapper.ge("age",30);//字段名字，年龄值
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

        //isNull、isNotNull   是空、非空
        //eq ne  等于、不等于
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("name","Tom");
        List<User> users2 = userMapper.selectList(wrapper2);
        System.out.println(users2);

        //between、notBetween  查询范围中的值
        QueryWrapper<User> wrapper3 = new QueryWrapper<>();
        wrapper3.between("age",20,30);
        List<User> users3 = userMapper.selectList(wrapper3);
        System.out.println(users3);

        //like   模糊查询
        QueryWrapper<User> wrapper4 = new QueryWrapper<>();
        wrapper4.like("name","y");
        List<User> users4 = userMapper.selectList(wrapper4);
        System.out.println(users4);

        //orderByDesc
        QueryWrapper<User> wrapper5 = new QueryWrapper<>();
        wrapper5.orderByDesc("id");
        List<User> users5 = userMapper.selectList(wrapper5);
        System.out.println(users5);

        //last
        QueryWrapper<User> wrapper6 = new QueryWrapper<>();
        wrapper6.last("limit 1");
        List<User> users6 = userMapper.selectList(wrapper6);
        System.out.println(users6);
    }


    //指定要查询的列
    @Test
    public void testSelectListColumn() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }




}
