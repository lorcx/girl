package com.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 第一个spring boot
 * Created by Administrator on 2017/10/19 0019.
 */
//@Controller
//@ResponseBody
@RestController
public class HelloController {
    // 直接引用配置文件中的值
    @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private int age;
    @Value("${content}")
    private String content;

    // 将配置写道类文件中
    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
//        return cupSize + age;
        return content;
//        return girlProperties.getCupSize();
//        return "index";
    }

    @RequestMapping(value = {"/hello1", "/h1"}, method = RequestMethod.GET)
    public String say1() {
//        return cupSize + age;
        return content;
//        return girlProperties.getCupSize();
//        return "index";
    }

    @RequestMapping(value = "/h2", method = RequestMethod.POST)
    public String say2() {
        return content;
    }

    @RequestMapping(value = "/h3/{id}", method = RequestMethod.GET)
    public String say3(@PathVariable int id) {
        return content + id;
    }

    @RequestMapping(value = "/{id}/h4", method = RequestMethod.GET)
    public String say4(@PathVariable int id) {
        return content + id;
    }

    @GetMapping("/{id}/h5")
    public String say5(@PathVariable int id) {
        return content + id;
    }

    @PostMapping("/{id}/h6")
    public String say6(@PathVariable int id) {
        return content + id;
    }

    @GetMapping("/h6")
    public String say7(@RequestParam int id) {
        return content + id;
    }

}
