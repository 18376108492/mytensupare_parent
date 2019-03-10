package com.itdan.mysupare.recruit.service;

import com.itdan.mysupare.recruit.dao.EnterpriseDAO;
import com.itdan.mysupare.recruit.pojo.Enterprise;
import entiy.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import java.util.List;

@Service
@Transactional
public class EnterpriseService {

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加企业操作
     * @param enterprise 企业对象
     * @return
     */
    public void add(Enterprise enterprise){
       enterpriseDAO.save(enterprise);
    }

    /**
     * 查询出热门企业
     * @param ishot 是否为热门状态
     * @return
     */
    public  List<Enterprise> findByIshot(String ishot){
        List<Enterprise>hotList= enterpriseDAO.findByIshot(ishot);
        return hotList;
    }
}
