package com.itdan.mysupare.recruit.dao;

import com.itdan.mysupare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 招聘业务持久层
 */
public interface RecruitDAO extends
        JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {
}
