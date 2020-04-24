package com.leyou.customer;

import com.leyou.client.UserClient;
import com.leyou.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("customer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;
    //@Autowired
    //private DiscoveryClient discoveryClient;// Eureka客户端，可以获取到服务实例信息

   /* @GetMapping("/{id}")//rest get 请求
    public User queryUserById(@PathVariable("id")Integer id){
        return restTemplate.getForObject("http://localhost:8081/user/"+id,User.class);

    }*/


/*   @GetMapping("{id}")
   public User queryUserById(@PathVariable("id")Long id){
       //根据服务名称，获取实例
       List<ServiceInstance> instances = discoveryClient.getInstances("com.leyou.user-service");
       //因为只有一个userService，因此直接get（0）获取
       ServiceInstance serviceInstance = instances.get(0);
       //获取ip和端口信息
       String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/com.leyou.user/"+id;
       User com.leyou.user = restTemplate.getForObject(url, User.class);
       return com.leyou.user;

   }*/

 /* @GetMapping("/{id}")//rest get 请求
    public User queryUserById(@PathVariable("id")Integer id){
        String url="http://user-Service/user/"+id;
        return restTemplate.getForObject(url,User.class);

    }*/

 /*  对单个方法开启熔断器
  @GetMapping("/{id}")//rest get 请求
 @HystrixCommand(fallbackMethod = "queryByIdFallback")
    public String queryUserById(@PathVariable("id")Integer id){
        String url="http://user-Service/user/"+id;
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;

    }

    public String queryByIdFallback(Integer id){
        return "人数太多，请稍后再试";
    }
*/
/* 对整个类开启熔断器
@GetMapping("/{id}")//rest get 请求
 //设置超时时间
 @HystrixCommand(commandProperties = {
    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "10000")
 } )
 public String queryUserById(@PathVariable("id")Integer id){
     String url="http://userService/user/"+id;
     String forObject = restTemplate.getForObject(url, String.class);
     return forObject;

 }*/

  /*  @GetMapping("/{id}")//rest get 请求
    //在yml里设置超时时间
    @HystrixCommand
    public String queryUserById(@PathVariable("id")Integer id){
        String url="http://userService/user/"+id;
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;


    }

    }
  public String defaultFallback(){

     return "人太多了,请稍后再试";
  }
    */


   /* //服务降级
    @GetMapping("/{id}")//rest get 请求
    //设置超时时间
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//意思是至少有10个请求才进行errorThresholdPercentage错误百分比计算
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//半开试探休眠时间，默认值5000ms。当熔断器开启一段时间之后比如10000ms，会尝试放过去一部分流量进行试探，确定依赖服务是否恢复。
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//设定错误百分比，默认值50%
    })
    public String queryUserById(@PathVariable("id") Integer id) {
        if(id % 2==0){
            throw  new RuntimeException("");
        }
        String url = "http://userService/user/" + id;
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;

    }

    public String defaultFallback(){
           return "人太多了,请稍后再试";
    }*/
        @GetMapping("/{id}")
        public User queryUserById(@PathVariable("id") Integer id) {

        return userClient.queryById(id);

    }
}