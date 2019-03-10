package com.itdan.mysupare.recruit.dao;

import com.itdan.mysupare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnterpriseDAO extends
        JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {

    /**
     * 查询出热门企业
     * @param ishot 是否为热门状态
     * @return
     */
      List<Enterprise> findByIshot(String ishot);//这样的命名规则，相当于 where ishost=?

}
