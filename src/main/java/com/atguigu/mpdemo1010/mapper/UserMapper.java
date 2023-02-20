package com.atguigu.mpdemo1010.mapper;

import com.atguigu.mpdemo1010.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
//IDEA在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确的执行。
//为了避免报错，可以在 dao 层 的接口上添加 @Repository 注

public interface UserMapper extends BaseMapper<User> {

    //接口实现BaseMapper并加上泛型，我们自己设计的UserMapper就能用mybatis plus里面的方法
}
