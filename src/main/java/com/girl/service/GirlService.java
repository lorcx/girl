package com.girl.service;

import com.girl.domain.Girl;
import com.girl.enums.ResultEnum;
import com.girl.exception.GirlException;
import com.girl.respository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 业务处理类
 */
@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    /**
     * 执行是否事务回滚
     */
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(22);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(33);
        girlRepository.save(girlB);
    }

    /**
     * aop 统一异常处理
     * @param id
     * @throws Exception
     */
    public void getAge(int id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        int age = girl.getAge();
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id获取女生信息 （测试）
     * @param id
     * @return
     */
    public Girl findOne(int id) {
        return girlRepository.findOne(id);
    }
}
