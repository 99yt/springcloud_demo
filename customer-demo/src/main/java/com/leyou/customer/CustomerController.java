package com.leyou.customer;

import com.leyou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")//rest get 请求
    public User queryUserById(@PathVariable("id")Integer id){
        return restTemplate.getForObject("http://localhost:8081/user/"+id,User.class);

    }
}
