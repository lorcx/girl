package com.girl.controller;

import com.girl.domain.Girl;
import com.girl.domain.Result;
import com.girl.respository.GirlRepository;
import com.girl.service.GirlService;
import com.girl.units.ResultUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {
    private static final Logger logger = LoggerFactory.getLogger(GirlController.class);
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> girlsList() {
        logger.info("girlsList 执行前");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * \@Valid 表示要验证这个对象
     *
     * @return
     */
    @PostMapping("/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUnit.error(0, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUnit.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     *
     * @param id
     * @return
     */
    @GetMapping("/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") int id) {
        return girlRepository.findOne(id);
    }

    /**
     * 更新女生
     *
     * @param id
     * @return
     */
    @PutMapping("/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") int id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") int age) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setId(id);
        return girlRepository.save(girl);
    }

    /**
     * 删除女生
     *
     * @param id
     */
    @DeleteMapping("/girls/{id}")
    public void girlDelete(@PathVariable("id") int id) {
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     */
    @GetMapping("/girls/age/{age}")
    public List<Girl> girlsListByAge(@PathVariable("age") int age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 事务处理
     */
    @PostMapping("/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    /**
     * 统一异常处理
     * @param id
     */
    @GetMapping("/girls/getAge/{id}")
    public void getAge(@PathVariable("id") int id) throws Exception {
        girlService.getAge(id);
    }
}
