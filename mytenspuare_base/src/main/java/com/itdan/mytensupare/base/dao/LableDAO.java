package com.itdan.mytensupare.base.dao;

import com.itdan.mytensupare.base.pojo.Lable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 标签的持久层
 */

public interface LableDAO extends JpaRepository<Lable,String>,
        JpaSpecificationExecutor<Lable> {

}
