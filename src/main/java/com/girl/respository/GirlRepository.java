package com.girl.respository;

import com.girl.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * dao
 */
public interface GirlRepository extends JpaRepository<Girl, Integer>{
    // 通过年龄查询
    public List<Girl> findByAge(int age);
}
