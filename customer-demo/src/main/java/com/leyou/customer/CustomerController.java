package com.leyou.customer;

import com.leyou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;
    //@Autowired
    //private DiscoveryClient discoveryClient;// Eureka客户端，可以获取到服务实例信息

   /* @GetMapping("/{id}")//rest get 请求
    public User queryUserById(@PathVariable("id")Integer id){
        return restTemplate.getForObject("http://localhost:8081/user/"+id,User.class);

    }*/


/*   @GetMapping("{id}")
   public User queryUserById(@PathVariable("id")Long id){
       //根据服务名称，获取实例
       List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
       //因为只有一个userService，因此直接get（0）获取
       ServiceInstance serviceInstance = instances.get(0);
       //获取ip和端口信息
       String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;
       User user = restTemplate.getForObject(url, User.class);
       return user;

   }*/

  @GetMapping("/{id}")//rest get 请求
    public User queryUserById(@PathVariable("id")Integer id){
      String url="http://user-Service/user/"+id;
        return restTemplate.getForObject(url,User.class);

    }

}
