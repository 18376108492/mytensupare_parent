package com.itdan.mytenspuare.pa.dao;

import com.itdan.mytenspuare.pa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * 数据访问接口
 *
 */
public interface ReplyDao extends JpaRepository<Reply,String>,
        JpaSpecificationExecutor<Reply>{
	
}
