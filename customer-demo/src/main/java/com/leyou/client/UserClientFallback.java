package com.leyou.client;

import com.leyou.pojo.User;
import org.springframework.stereotype.Component;

@Component //
public class UserClientFallback implements UserClient{
    @Override
    public User queryById(Integer id) {
        User user = new User();
        user.setName("hehe,人数太多,稍后重试");
        return user;
    }
}
